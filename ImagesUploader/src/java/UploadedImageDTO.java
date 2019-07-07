
import java.io.File;
import java.io.Serializable;

/**
 *
 * @author tamer
 */
public class UploadedImageDTO implements Serializable
{
    private File uploadedImage;
    private String imageRelativePath;
    
    public UploadedImageDTO()
    {
    }

    public File getUploadedImage()
    {
        return uploadedImage;
    }

    public void setUploadedImage(File uploadedImage)
    {
        this.uploadedImage = uploadedImage;
    }

    public String getImageRelativePath()
    {
        return imageRelativePath;
    }

    public void setImageRelativePath(String imageRelativePath)
    {
        this.imageRelativePath = imageRelativePath;
    }

    @Override
    public String toString() {
        return "UploadedImageDTO{" + "uploadedImage=" + uploadedImage + ", imageRelativePath=" + imageRelativePath + '}';
    }
    
    
    
}// end of class UploadedImageDTO