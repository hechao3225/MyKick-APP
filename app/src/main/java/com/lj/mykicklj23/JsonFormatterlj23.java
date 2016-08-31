package com.lj.mykicklj23;

import java.util.ArrayList;

/**
 * Created by LJ on 2016/6/20.
 */
public class JsonFormatterlj23 {
    /**
     * json字符串的格式化
     *
     * @author peiyuxin
     * @param json
     *            需要格式的json串
     * @param fillStringUnit每一层之前的占位符号比如空格
     *            制表符
     * @return
     */
    public static String formatJsonlj23(String json, String fillStringUnit) {
        if (json == null || json.trim().length() == 0) {
            return null;
        }

        int fixedLenthlj23 = 0;
        ArrayList<String> tokenListlj23 = new ArrayList<String>();
        {
            String jsonTemplj23 = json;
            // 预读取
            while (jsonTemplj23.length() > 0) {
                String token = getTokenlj23(jsonTemplj23);
                jsonTemplj23 = jsonTemplj23.substring(token.length());
                token = token.trim();
                tokenListlj23.add(token);
            }
        }

        for (int i = 0; i < tokenListlj23.size(); i++) {
            String token = tokenListlj23.get(i);
            int length = token.getBytes().length;
            if (length > fixedLenthlj23 && i < tokenListlj23.size() - 1 && tokenListlj23.get(i + 1).equals(":")) {
                fixedLenthlj23 = length;
            }
        }

        StringBuilder buflj23 = new StringBuilder();
        int count = 0;
        for (int i = 0; i < tokenListlj23.size(); i++) {

            String token = tokenListlj23.get(i);

            if (token.equals(",")) {
                buflj23.append(token);
                doFilllj23(buflj23, count, fillStringUnit);
                continue;
            }
            if (token.equals(":")) {
                buflj23.append(" ").append(token).append(" ");
                continue;
            }
            if (token.equals("{")) {
                String nextToken = tokenListlj23.get(i + 1);
                if (nextToken.equals("}")) {
                    i++;
                    buflj23.append("{ }");
                } else {
                    count++;
                    buflj23.append(token);
                    doFilllj23(buflj23, count, fillStringUnit);
                }
                continue;
            }
            if (token.equals("}")) {
                count--;
                doFilllj23(buflj23, count, fillStringUnit);
                buflj23.append(token);
                continue;
            }
            if (token.equals("[")) {
                String nextToken = tokenListlj23.get(i + 1);
                if (nextToken.equals("]")) {
                    i++;
                    buflj23.append("[ ]");
                } else {
                    count++;
                    buflj23.append(token);
                    doFilllj23(buflj23, count, fillStringUnit);
                }
                continue;
            }
            if (token.equals("]")) {
                count--;
                doFilllj23(buflj23, count, fillStringUnit);
                buflj23.append(token);
                continue;
            }

            buflj23.append(token);
            // 左对齐
            if (i < tokenListlj23.size() - 1 && tokenListlj23.get(i + 1).equals(":")) {
                int fillLength = fixedLenthlj23 - token.getBytes().length;
                if (fillLength > 0) {
                    for (int j = 0; j < fillLength; j++) {
                        buflj23.append(" ");
                    }
                }
            }
        }
        return buflj23.toString();
    }

    private static String getTokenlj23(String json) {
        StringBuilder buflj23 = new StringBuilder();
        boolean isInYinHaolj23 = false;
        while (json.length() > 0) {
            String token = json.substring(0, 1);
            json = json.substring(1);

            if (!isInYinHaolj23 && (token.equals(":") || token.equals("{") || token.equals("}") || token.equals("[") || token.equals("]") || token.equals(","))) {
                if (buflj23.toString().trim().length() == 0) {
                    buflj23.append(token);
                }

                break;
            }

            if (token.equals("\\")) {
                buflj23.append(token);
                buflj23.append(json.substring(0, 1));
                json = json.substring(1);
                continue;
            }
            if (token.equals("\"")) {
                buflj23.append(token);
                if (isInYinHaolj23) {
                    break;
                } else {
                    isInYinHaolj23 = true;
                    continue;
                }
            }
            buflj23.append(token);
        }
        return buflj23.toString();
    }

    private static void doFilllj23(StringBuilder buf, int count, String fillStringUnit) {
        buf.append("\n");
        for (int i = 0; i < count; i++) {
            buf.append(fillStringUnit);
        }
    }

}
