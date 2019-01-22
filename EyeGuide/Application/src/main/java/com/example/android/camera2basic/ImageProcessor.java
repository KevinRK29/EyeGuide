package com.example.android.camera2basic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.MediaPlayer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Random;

public class ImageProcessor implements Runnable {

    /**
     * The JPEG image
     */
    private final Image mImage;

    ImageProcessor(Image image) {
        mImage = image;
    }

    @Override
    public void run() {
        ByteBuffer buffer = mImage.getPlanes()[0].getBuffer();

        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        System.out.println(Arrays.toString(bytes));
        Bitmap bitmapImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, null);

        mImage.close();

        // IMAGE RECOGNITION TO IDENTIFY THE OBJECT

        // Since image recognition wasn't working by the time submissions are due, we will just
        // randomize the output for the scope of demoing
        Random random = new Random();
        int num = random.nextInt(3);

        MediaPlayer mediaPlayer;

        switch(num) {
            case 0:
                mediaPlayer = MediaPlayer.create(CameraActivity.getLastSetContext(), R.raw.person_nearby);
                break;
            case 1:
                mediaPlayer = MediaPlayer.create(CameraActivity.getLastSetContext(), R.raw.object_nearby);
                break;
            case 2:
                mediaPlayer = MediaPlayer.create(CameraActivity.getLastSetContext(), R.raw.road_nearby);
                break;
            default:
                mediaPlayer = MediaPlayer.create(CameraActivity.getLastSetContext(), R.raw.object_nearby);
                break;
        }

        mediaPlayer.start();
    }

}