import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

public class Test {
    public Test() {
    }

    public static void build(OutputStream output, String json, String[] properties) {
        build(output, json, properties, properties);
    }

    public static void build(OutputStream output, String json, String[] properties, String[] columnsNames) {
        HSSFWorkbook libro = new HSSFWorkbook();
        HSSFSheet hoja = libro.createSheet();
        HSSFRow header = hoja.createRow(0);
        JSONArray array = new JSONArray(json);

        int e;
        for(e = 0; e < columnsNames.length; ++e) {
            String object = columnsNames[e];
            HSSFCell row = header.createCell(e);
            HSSFRichTextString j = new HSSFRichTextString(object);
            row.setCellValue(j);
        }

        for(e = 0; e < array.length(); ++e) {
            JSONObject var16 = array.getJSONObject(e);
            HSSFRow var17 = hoja.createRow(e + 1);

            for(int var18 = 0; var18 < properties.length; ++var18) {
                String string = properties[var18];
                HSSFCell cell = var17.createCell(var18);
                try {

                    HSSFRichTextString text = new HSSFRichTextString(var16.get(string).toString());
                    cell.setCellValue(text);
                } catch (Exception var15) {
//                    var15.printStackTrace();
//                    HSSFRichTextString text = new HSSFRichTextString(var16.getDouble(string) + "");
//                    cell.setCellValue(text);
                }

            }
        }
        try {
            libro.write(output);
        } catch (Exception var15) {
            var15.printStackTrace();
        }
    }
    public static void main(String [] args){
        try {
            FileReader reader = new FileReader(new File("d:\\q.json"));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = null;
            StringBuffer stringBuffer = new StringBuffer();
            while ((line= bufferedReader.readLine()) != null){
                stringBuffer.append(line);
            }
            FileOutputStream xlsStream = new FileOutputStream("d:\\result.xls");
            Test.build(xlsStream, stringBuffer.toString(), new String[]{"EXP","EXP1","EXP2","EXP_OFFSET","IDEFAULT","ISTAT","MEMO","PK_ACCOUNT_CRT","PK_ACCOUNT_CRT_NAME","PK_RULE","PK_RULE_CODE","PK_RULE_NAME","PK_SCHEDULE","SCHEDULE_CODE","SCHEDULE_NAME","WORK_TIME","pageNo","pageSize","totalCount","totalPageCount"});
            xlsStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}