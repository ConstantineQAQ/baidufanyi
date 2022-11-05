package entil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class JsonEntil {
    public void writeJsonFile(String from){
        // 读取原始json文件并进行操作和输出
        try {
            String s1 = readFileByLines("src/main/resources/json.config");    // 读取配置文件
            String ws = null;
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/json.config"));   // 输出配置文件
            JSONArray dataJson = JSONArray.parseArray(s1);
            for (int i = 0; i < dataJson.size(); i++) {
                JSONObject properties = (JSONObject) dataJson.get(i);
                properties.put("from",from);     //  需要替换的参数
                ws = dataJson.toString();
                System.out.println(ws);
            }
            bw.write(ws);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFileByLines(String fileName) {
        FileInputStream file = null;
        BufferedReader reader = null;
        InputStreamReader inputFileReader = null;
        String content = "";
        String tempString = null;
        try {
            file = new FileInputStream(fileName);
            inputFileReader = new InputStreamReader(file, "utf-8");
            reader = new BufferedReader(inputFileReader);
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                content += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return content;
    }
}
