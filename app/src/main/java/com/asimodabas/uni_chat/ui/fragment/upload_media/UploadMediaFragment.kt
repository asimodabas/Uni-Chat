package com.asimodabas.uni_chat.ui.fragment.upload_media

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.databinding.FragmentUploadMediaBinding
import com.asimodabas.uni_chat.viewBinding

class UploadMediaFragment : Fragment(R.layout.fragment_upload_media) {

    private val binding by viewBinding(FragmentUploadMediaBinding::bind)
    private val viewModel: UploadMediaViewModel by viewModels()
    private var selectedBitmap: Bitmap? = null
    private val args: UploadMediaFragmentArgs by navArgs()

    private val imagePickerLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.setSelectedImage(it)
            displaySelectedImage(it)
        }
    }

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            imagePickerLauncher.launch("image/*")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        viewModel.selectedImage.observe(viewLifecycleOwner) { uri ->
            uri?.let { displaySelectedImage(it) }
        }

        viewModel.uploadSuccess.observe(viewLifecycleOwner) { success ->
            if (success) {
                Toast.makeText(
                    requireContext(),
                    R.string.successful_sharing,
                    Toast.LENGTH_LONG
                ).show()
                findNavController().navigateUp()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.UploadButton.isEnabled = !isLoading
        }
    }

    private fun setupClickListeners() {
        binding.uploadImageView.setOnClickListener {
            checkPermissionAndSelectImage()
        }

        binding.UploadButton.setOnClickListener {
            viewModel.uploadMedia(args.departmantId)
        }
    }

    private fun checkPermissionAndSelectImage() {
        activity?.let {
            if (ContextCompat.checkSelfPermission(
                    it.applicationContext,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            } else {
                imagePickerLauncher.launch("image/*")
            }
        }
    }

    private fun displaySelectedImage(uri: Uri) {
        try {
            context?.let { context ->
                if (Build.VERSION.SDK_INT >= 28) {
                    val source = ImageDecoder.createSource(context.contentResolver, uri)
                    selectedBitmap = ImageDecoder.decodeBitmap(source)
                    binding.uploadImageView.setImageBitmap(selectedBitmap)
                } else {
                    selectedBitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                    binding.uploadImageView.setImageBitmap(selectedBitmap)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(
                requireContext(),
                "Failed to load image: ${e.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}