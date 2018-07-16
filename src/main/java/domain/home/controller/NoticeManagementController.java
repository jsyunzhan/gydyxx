package domain.home.controller;

import domain.home.entity.NoticeEntity;
import domain.home.service.NoticeManagementService;
import domain.shiro.controller.AbstractActionController;
import domain.shiro.entity.JsonResponseVO;
import domain.shiro.entity.PageQueryResult;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.shiro.codec.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static domain.home.HomeWebForward.TO_NOTICE_PAGE;
import static domain.home.HomeWebURLMapping.*;

@Controller
public class NoticeManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeManagementController.class);

    final private NoticeManagementService noticeManagementService;

    @Autowired
    public NoticeManagementController(NoticeManagementService noticeManagementService){
        this.noticeManagementService = noticeManagementService;
    }

    /**
     * 去消息管理页面
     * @return ModelAndView
     */
    @RequestMapping(value = NOTICE_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_NOTICE_PAGE);
    }

    /**
     *  首页公告分页
     * @param noticeEntity noticeEntity
     * @return PageQueryResult
     */
    @RequestMapping(value = NOTICE_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult noticeListInfo(NoticeEntity noticeEntity){
        return noticeManagementService.noticeListInfo(noticeEntity);
    }

    /**
     * 主页展示
     * @param noticeEntity 主页展示
     * @return List<NoticeEntity>
     */
    @RequestMapping(value = "/homepage/notice/list")
    @ResponseBody
    public List<NoticeEntity> noticeAllList(NoticeEntity noticeEntity){
        return noticeManagementService.noticeAllList(noticeEntity);
    }

    /**
     * 公告新增
     * @param noticeEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = NOTICE_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO noticeAdd(@RequestBody NoticeEntity noticeEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        noticeEntity.setCreateUserId(getLoginId());

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("公告消息新增,title:{}",noticeEntity.getNoticeTitle());
            }
            Boolean flag =  noticeManagementService.noticeAdd(noticeEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }

    @RequestMapping(value = NOTICE_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO noticeEdit(@RequestBody NoticeEntity noticeEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        noticeEntity.setUpdateUserId(getLoginId());

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("公告消息修改,title:{}",noticeEntity.getNoticeTitle());
            }
            Boolean flag =  noticeManagementService.noticeEdit(noticeEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }

    @RequestMapping(value = NOTICE_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO noticeDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("公告消息删除,id:{}",id);
            }
            Boolean flag =  noticeManagementService.noticeDelete(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }

    /**
     * 通知管理图片显示
     * @param fileByte 文件
     * @return List<String>
     * @throws IOException io异常
     */
    @RequestMapping(value = NOTICE_MANAGEMENT_PICTURE_DETAIL)
    @ResponseBody
    public List<String> pictureDetail(@RequestParam("file")MultipartFile[] fileByte) throws IOException {

        List<String> strings = newArrayList();

        for (MultipartFile file:
        fileByte) {
            final String string64 = Base64.encodeToString(file.getBytes());
            strings.add(string64);
        }
        return strings;
    }

    /**
     * 图片上传
     * @param fileArray 文件数组
     * @return String 文件存放路径
     * @throws IOException io异常
     */
    @RequestMapping(value = NOTICE_MANAGEMENT_PICTURE_UPLOAD,produces="text/html; charset=UTF-8")
    @ResponseBody
    public String pictureComment(@PathVariable("name") String name,@RequestParam("file") MultipartFile[] fileArray) throws IOException {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH )+1;
        int data = cal.get(Calendar.DATE);
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        //用户名称名称
        final String userName = getUserName();

        String picturePath = "";

        for (int i=0;i<fileArray.length;i++){
            MultipartFile file = fileArray[i];

            if(!file.isEmpty()) {

                //文件存放路径
                String dirPath = "D:/image/"+name+"/" + userName + "/" + year + "/" + month + "/" +data;
                //创建文件夹
                File dir = new File(dirPath);
                if (!dir.exists()){
                    dir.mkdirs();
                }

                //获取上传文件的文件名
                String oFileName=file.getOriginalFilename();
                //截取文件后缀名
                String suffix = oFileName.substring(oFileName.indexOf("."),oFileName.length());

                //文件名
                String newFileName = year + "" + month + "" + data + "" + hour + "" + minute + "" + second + "" + i +suffix;

                //文件的绝对路径
                String realPath = dirPath+"/"+newFileName;
                picturePath += realPath;
                picturePath += ",";
                //创建文件
                File tempFile = new File(realPath);

                try {
                    // 先尝试压缩并保存图片
                    Thumbnails.of(file.getInputStream()).scale(1f).outputQuality(0.25f).toFile(tempFile);
                } catch (IOException e) {
                    //文件转换
                    file.transferTo(tempFile);
                }
            }
        }

        return picturePath;
    }

    /**
     *
     * @param noticeEntity
     * @return
     * @throws IOException
     */
    @RequestMapping(value = NOTICE_MANAGEMENT_PICTURE_SHOW)
    @ResponseBody
    public String[] getPictureByte(@RequestBody NoticeEntity noticeEntity) throws IOException {

        //获取图片路径
        final String path = noticeEntity.getPicturePath();
        //获取图片路径地址
        final String[] pathArr=path.split(",");
        //需要返回的base64数组
        String[] base64Array = new String[pathArr.length];

        for (int i=0;i<pathArr.length;i++){
            //图片地址
            String pahtStr = pathArr[i];
            //获取数组
            byte[] imageByte = Files.readAllBytes(Paths.get(pahtStr));
            //转码
            String base64String= java.util.Base64.getEncoder().encodeToString(imageByte);
            //添加到64数组
            base64Array[i] = base64String;
        }
        return base64Array;
    }
}
