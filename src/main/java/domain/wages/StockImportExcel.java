package domain.wages;


import domain.shiro.dao.AccountDao;
import domain.shiro.entity.AccountEntity;
import domain.wages.entity.JsonVOArray;
import domain.wages.entity.WagesEntity;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static domain.until.ImportUtil.getCellData;
import static domain.until.ImportUtil.getWorkBook;

@Service
public class StockImportExcel {
    private static final Logger LOGGER = LoggerFactory.getLogger(StockImportExcel.class);

    final private AccountDao accountDao;

    @Autowired
    public StockImportExcel(AccountDao accountDao){
        this.accountDao = accountDao;
    }

    public JsonVOArray analysisExcel(MultipartFile file){

        final JsonVOArray jsonVOArray = new JsonVOArray();

        final List<WagesEntity> wagesEntities = newArrayList();

        try {
            //获取工作簿
            final Workbook workBook = getWorkBook(file);
            //读取第一个标签
            final Sheet sheet = workBook.getSheetAt(0);
            final Integer rowCount = sheet.getLastRowNum();

            //获取第一行
            Row first=sheet.getRow(0);
            //列数
            final Integer cellCount = first.getPhysicalNumberOfCells();

//            从上往下[0开始数]第1行存在所需数据
            for (int i = 1; i <= rowCount; i++) {
                final Row row = sheet.getRow(i);
                String wagesDetails = "";
                AccountEntity accountEntity = new AccountEntity();
                if (null != row){
                    final String userName = getCellData(row,0);
                    accountEntity = accountDao.checkUserName(userName);

                    if (null != accountEntity){
                        for (int y=1; y < cellCount; y++){
                            final String key = getCellData(sheet.getRow(0),y);
                            final String value = getCellData(row, y);
                            String rowString = key+":"+value+";";
                            wagesDetails += rowString;
                        }
                    }else {
                        jsonVOArray.setSuccess(Boolean.FALSE);
                        jsonVOArray.setReason("姓名：'"+userName+"'不存在，请检查！");
                        return jsonVOArray;
                    }


                }else {
                    jsonVOArray.setSuccess(Boolean.FALSE);
                    jsonVOArray.setReason("存在空的单元格，请检查！");
                    return jsonVOArray;
                }
                final WagesEntity wagesEntity = new WagesEntity();
                wagesEntity.setWagesdetails(wagesDetails);
                wagesEntity.setAccountId(accountEntity.getId());
                wagesEntity.setAccountName(accountEntity.getUserName());
                wagesEntities.add(wagesEntity);
            }
        } catch (Exception e) {
            jsonVOArray.setSuccess(Boolean.FALSE);
            LOGGER.error("analysis excel error:{}", e);
        }

        jsonVOArray.setWagesEntityList(wagesEntities);
        return jsonVOArray;


    }

}
