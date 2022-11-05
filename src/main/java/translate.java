import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import utils.TransApi;


import java.io.File;
import java.util.Scanner;

public class translate {
    public static void main(String[] args) {
        TransApi api = new TransApi("20220821001314661", "fae8vmmnU6vqrezAmVNB");
        while(true){
            translateReply(api);
        }


    }
    public static void translateReply(TransApi api)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入:");
        String s = sc.nextLine();
        String trans_in = s;
        if (trans_in.matches("^(?:(?:翻译)|(?:翻译一下)|(?:解释)|(?:解释一下)|(?:translate))[\\s]*$"))
        {
            System.out.println(("请输入正确的表达式，用法示例：" +
                    "\n" + "翻译 apple" +
                    "\n" + "翻译一下 apple"));
        } else if (trans_in.contains("翻译") || trans_in.contains("翻译一下") )
        {
            if (trans_in.length() > trans_in.indexOf(' '))
            {
                String trans = trans_in.substring(trans_in.indexOf(' '));
                JSONObject jsonObject = JSONObject.parseObject(api.getTransResult(trans, "auto", "zh"));
                JSONArray trans_result = jsonObject.getJSONArray("trans_result");
                String result = trans_result.getJSONObject(0).get("dst").toString();
                if (jsonObject.getString("from").equals("zh"))
                {
                    System.out.println("你输入的可能是中文，请重新输入");
                } else
                {
                    System.out.println("上面这句话的中文翻译为：" + result +
                            "\n" + "来源于" + jsonObject.getString("from") + "语种");
                }
            }
        }else if(trans_in.equals("退出")){
            System.exit(1);
    }
    }
}
