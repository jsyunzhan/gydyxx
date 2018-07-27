package domain.until;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.util.StringUtils.endsWithIgnoreCase;
import static org.springframework.util.StringUtils.trimAllWhitespace;

/**
 * Created by Lowe.yang on 6/13/2017.
 * 文件导入工具类
 */
public final class ImportUtil {
    private static final Logger LOGGER = getLogger(ImportUtil.class);

    private ImportUtil() {
    }

    /**
     * 根据excel文件获取对应的工作簿类
     * @param file 文件
     * @return 工作簿实体
     */
    public static Workbook getWorkBook(MultipartFile file) throws Exception{
        final Workbook workbook;
        try {
            if (endsWithIgnoreCase(file.getOriginalFilename(), "xls")) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else if (endsWithIgnoreCase(file.getOriginalFilename(), "xlsx")) {
                workbook = new XSSFWorkbook(file.getInputStream());
            } else {
                throw new Exception("读取Excel错误");
            }
            return workbook;
        } catch (IOException e) {
            LOGGER.error("read excel is error!:{}", e);
            throw new Exception("读取Excel错误");
        }
    }


    /**
     * 获取excel单元格数据
      * @param row 行
     * @param cellNum 列号
     * @return 单元格数据
     */
    public static String getCellData(Row row, int cellNum) {
        Cell cell = row.getCell(cellNum);
        if (cell == null) {
            return "";
        }
        int type = cell.getCellType();
        String returnValue = null;
        switch (type) {
            case Cell.CELL_TYPE_STRING:
                returnValue = trimAllWhitespace(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                returnValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                returnValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_BLANK:
                returnValue = "";
                break;
            default:
                LOGGER.error("read excel is error!");
                break;
        }
        return returnValue;
    }
}
