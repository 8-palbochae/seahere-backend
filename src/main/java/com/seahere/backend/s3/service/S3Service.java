package com.seahere.backend.s3.service;

import com.seahere.backend.s3.repository.S3Repository;
import com.seahere.backend.user.domain.UserEntity;
import com.seahere.backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class S3Service {

    private final S3Repository s3Repository;
    private final UserRepository userRepository;


    public S3Service(S3Repository s3Repository, UserRepository userRepository) {
        this.s3Repository = s3Repository;
        this.userRepository = userRepository;
    }

    public String uploadFileAndUpdateUserProfile(MultipartFile file, Long userId) {
        String originalFilename = file.getOriginalFilename();
        String extension = "";

        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        String fileName = "users/" + userId.toString() + "_"+ originalFilename;
        File convertedFile = convertMultiPartFileToFile(file);
        s3Repository.uploadFileToS3(fileName, convertedFile.getAbsolutePath());
        convertedFile.delete();
        String fileUrl = fileName;

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.updateProfileImage(fileUrl);
        userRepository.save(user);

        return fileUrl;
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Error converting multipart file to file", e);
        }
        return convertedFile;
    }

    public void deleteFile(String fileName) {
        s3Repository.deleteFileFromS3(fileName);
    }

    public String getUserProfileImageUrl(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        String profileImageUrl = user.getProfileImage();
        if (profileImageUrl != null) {
            return profileImageUrl;
        } else {
            throw new RuntimeException("Profile image not found for user: " + userId);
        }
    }

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }
}
