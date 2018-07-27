package domain.wages.controller;

import domain.shiro.controller.AbstractActionController;
import domain.shiro.entity.AccountEntity;
import domain.shiro.entity.JsonResponseVO;
import domain.shiro.entity.PageQueryResult;
import domain.wages.StockImportExcel;
import domain.wages.entity.JsonVOArray;
import domain.wages.entity.RequsetVOEntity;
import domain.wages.entity.WagesEntity;
import domain.wages.entity.WagesMainEntity;
import domain.wages.service.WagesManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static domain.wages.WagesWebForward.TO_WAGES_PAGE;
import static domain.wages.WagesWebURLMapping.*;

@Controller
public class WagesManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(WagesManagementController.class);

    final private WagesManagementService wagesManagementService;
    final private StockImportExcel stockImportExcel;

    @Autowired
    public WagesManagementController(WagesManagementService wagesManagementService,StockImportExcel stockImportExcel){
        this.wagesManagementService = wagesManagementService;
        this.stockImportExcel = stockImportExcel;
    }


    /**
     * 去工资管理页面
     * @return ModelAndView
     */
    @RequestMapping(value = WAGES_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_WAGES_PAGE);
    }

    /**
     * 工资页分页
     * @param wagesMainEntity 查询实体
     * @return PageQueryResult
     */
    @RequestMapping(value = WAGES_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult wagesMainList(WagesMainEntity wagesMainEntity){
        return wagesManagementService.wagesMainList(wagesMainEntity);
    }

    /**
     * 获取用户表数据
     * @return List<AccountEntity>
     */
    @RequestMapping(value = WAGES_MANAGEMENT_ACCOUNT_ALL_LIST)
    @ResponseBody
    public List<AccountEntity> accountAllList(){
        return wagesManagementService.accountAllList();
    }

    /**
     * 表格上传
     */
    @RequestMapping(value = WAGES_MANAGEMENT_EXCEL_IMPORT)
    @ResponseBody
    public JsonVOArray excel(@RequestParam("file") MultipartFile file){
        return stockImportExcel.analysisExcel(file);
    }

    /**
     * 工资新增
     * @param requsetVOEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = WAGES_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO wagesAdd(@RequestBody RequsetVOEntity requsetVOEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("工资新增新增,title:{}",requsetVOEntity.getWagesMainEntity().getWagesName());
            }
            final Boolean flag = wagesManagementService.wagesAdd(requsetVOEntity,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }

    /**
     * 工资修改
     * @param requsetVOEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = WAGES_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO wagesEdit(@RequestBody RequsetVOEntity requsetVOEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("工资修改,title:{}",requsetVOEntity.getWagesMainEntity().getWagesName());
            }
            final Boolean flag = wagesManagementService.wagesEdit(requsetVOEntity,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 工资查看详情
     * @param id id
     * @return List<WagesEntity>
     */
    @RequestMapping(value = WAGES_MANAGEMENT_DETAILS)
    @ResponseBody
    public List<WagesEntity> wagesDetails(@PathVariable("id") Long id){
        return wagesManagementService.wagesDetails(id);
    }

    /**
     * 工资删除
     * @return JsonResponseVO
     */
    @RequestMapping(value = WAGES_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO wagesDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("工资删除,id:{}",id);
            }
            final Boolean flag = wagesManagementService.wagesDelete(id);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }
}
