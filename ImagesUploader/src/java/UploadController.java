
import com.masary.database.MasaryManager;
import com.masary.utils.SystemSettingsUtil;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

public class UploadController extends HttpServlet {

    private String uploadDirectory = null;
    private String urlPrefix = null;

    static ArrayList<String> urlsArr = new ArrayList<String>();

    private static Logger log = Logger.getLogger(UploadController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        log.info("UploadController Start.");
        log.info("UploadController, Start getting Info from property file.");
        try {
            uploadDirectory = SystemSettingsUtil.getInstance().loadProperty("upload.local.path");
            log.info("UploadController, Success to get {upload.local.path} from property file: " + uploadDirectory);
            urlPrefix = SystemSettingsUtil.getInstance().loadProperty("upload.url.prefix");
            log.info("UploadController, Success to get {upload.url.prefix} from property file: " + urlPrefix);

        } catch (Exception e) {
            log.error("UploadController, Error while loading upload info from property file. ", e);
        }
        log.info("UploadController, End getting Info from property file.");

        response.setContentType("text/html;charset=UTF-8");

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        String randomFolderName = date.toString();
        PrintWriter out = response.getWriter();
        String action = "";
        String phoneNum = "";
        String uploadDate = "";
        String encId = "";
        String encPass = "";
        
        try {
            boolean ismulti = ServletFileUpload.isMultipartContent(request);
            if (!ismulti) {
            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (Exception e) {
                    log.error(e.getMessage(),e);
                }
                Iterator itr = items.iterator();
                log.info("UploadController, start items proccessing");
                while (itr.hasNext()) {
                    FileItem item = (FileItem) itr.next();
                    if (item.isFormField()) {
                        String fieldName = item.getFieldName();
                        if (fieldName.equals("buttonIndex")) {
                            action = item.getString();
                            log.info(action);
                        }
                        if (fieldName.equals("uploadDate")) {
                            uploadDate = item.getString();
                            log.info(uploadDate);
                        }
                        if (fieldName.equals("phoneNum")) {
                            phoneNum = item.getString();
                            log.info(phoneNum);
                        }
                        if (fieldName.equals("encID")) {
                            encId = item.getString();
                            log.info("received encrypted id from client : " + encId);
//                            System.out.println("id ++++++++++++++ " + encId);
                        }
                        if (fieldName.equals("encPass")) {
                            encPass = item.getString();
                            log.info("received encrypted password from client : " + encPass);
//                            System.out.println("pass ++++++++++++ " + encPass);
                        }
                    } else {
                        randomFolderName = date.toString();
                        String ItemName = item.getName();
                        if (ItemName == null || ItemName.equals("")) {
                            continue;
                        }
                        String FileName = FilenameUtils.getName(ItemName);
                        
                        if(MasaryManager.getInstance().checkCustomer(encId, encPass)){
                            UploadedImageDTO uploadedImageDTO = checkExist(action, randomFolderName, FileName, uploadDate, phoneNum);
                            item.write(uploadedImageDTO.getUploadedImage());
                            log.info("UploadController, image written in hard disk");
                            out.println(urlPrefix + uploadedImageDTO.getImageRelativePath());
                            log.info("uploaded image url: " + urlPrefix + uploadedImageDTO.getImageRelativePath());
                        }
                    }
                }
                log.info("UploadController, end items proccessing");
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            out.println("Error During Upload Images");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
//            Logger.getLogger(UploadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    private UploadedImageDTO checkExist(String fileName, String randomFolderName, String fileExtension,
            String uploadDate, String phoneNum) {
        log.info("UploadController, Start checkExist: fileName=" + fileName + ",randomFolderName=" + randomFolderName + ",fileExtension=" + fileExtension
                + ",uploadDate=" + uploadDate + ",phoneNum=" + phoneNum + " .");
        UploadedImageDTO uploadedImageDTO = new UploadedImageDTO();

        String[] datearr = randomFolderName.split("-");
        File folderName = new File(uploadDirectory + "/" + datearr[0]);
//        StringBuffer fname = new StringBuffer(fileExtension);
        String ext = FilenameUtils.getExtension(fileExtension);
        if (!folderName.exists()) {
            folderName.mkdir();
            log.info("UploadController, path Create ," + uploadDirectory + "/" + datearr[0]);

        } else {
            log.info("UploadController, path exist ," + uploadDirectory + "/" + datearr[0]);
        }
        folderName = new File(uploadDirectory + "/" + datearr[0] + "/" + datearr[1]);
        if (!folderName.exists()) {
            folderName.mkdir();
            log.info("UploadController, Create ," + uploadDirectory + "/" + datearr[0] + "/" + datearr[1]);
        } else {
            log.info("UploadController, path  exist ," + uploadDirectory + "/" + datearr[0] + "/" + datearr[1]);
        }
        folderName = new File(uploadDirectory + "/" + datearr[0] + "/" + datearr[1] + "/" + datearr[2]);
        if (!folderName.exists()) {
            folderName.mkdir();
            log.info("UploadController, path Create ," + uploadDirectory + "/" + datearr[0] + "/" + datearr[1] + "/" + datearr[2]);
        } else {
            log.info("UploadController, path exist ," + uploadDirectory + "/" + datearr[0] + "/" + datearr[1] + "/" + datearr[2]);
        }
        folderName = new File(uploadDirectory + "/" + datearr[0] + "/" + datearr[1] + "/" + datearr[2] + "/" + (uploadDate + "-" + phoneNum));
        if (!folderName.exists()) {
            folderName.mkdir();
            log.info("UploadController, path Create ," + uploadDirectory + "/" + datearr[0] + "/" + datearr[1] + "/" + datearr[2] + "/" + (uploadDate + "-" + phoneNum));

        } else {
            log.info("UploadController, path exist ," + uploadDirectory + "/" + datearr[0] + "/" + datearr[1] + "/" + datearr[2] + "/" + (uploadDate + "-" + phoneNum));
        }

        String imageRelativePath = "/" + datearr[0] + "/" + datearr[1] + "/" + datearr[2] + "/" + (uploadDate + "-" + phoneNum) + "/" + (fileName + "." + ext);
        folderName = new File(uploadDirectory + imageRelativePath);
        log.info("UploadController, File Create, " + uploadDirectory + imageRelativePath);
        uploadedImageDTO.setUploadedImage(folderName);
        uploadedImageDTO.setImageRelativePath(imageRelativePath);

        log.info("UploadController, End checkExist. and return : " + uploadedImageDTO.toString());
//        if (folderName.exists()) {
//            StringBuffer sb = new StringBuffer(fileName);
//            sb.insert(sb.lastIndexOf("."), "-" + new Date().getTime());
//            folderName = new File(FileSave + "/" + datearr[0] + "/" + datearr[1] + "/" + datearr[2] + "/" + fileName);
//        }
        return uploadedImageDTO;
    }

}
