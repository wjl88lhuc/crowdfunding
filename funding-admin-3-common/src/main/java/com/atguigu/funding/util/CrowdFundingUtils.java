package com.atguigu.funding.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Map;

public class CrowdFundingUtils {

    /**
     * 校验Map映射
     * @param map
     * @param <K>
     * @param <V>
     * @return false表示无效，true表示有效
     */
    public static <K,V> boolean mapEffective(Map<K,V> map){
        return map != null && map.size() > 0;
    }

    /**
     * 判断集合是否有效
     * @param collection
     * @param <E>
     * @return false表示无效，true表示有效
     */
    public static <E> boolean collectionEffective(Collection<E> collection){
        return collection != null && collection.size() > 0;
    }

    /**
     * 检查字符串是否有效
     * @param source
     * @return false表示无效，true表示有效
     */
    public static boolean stringEffective(String source){
        return source != null && source.length() > 0;
    }

    /**
     * md5加密方法
     * 输入明文，返回密文
     * @param source
     * @return false表示无效，true表示有效
     */
    public static String md5(String source){
        //检查字符串是否有效
        if (!stringEffective(source)){
            //检查字符串无效，通过抛出异常的方法来通知调用者来处理
            throw new RuntimeException(CrowdFundingConstant.MESSAGE_CODE_INVALID);
        }
        StringBuilder builder = new StringBuilder();
        //准备字符数组
        char[] characters = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        //指定加密算法
        String algorithm = "MD5";
        try {
            //执行加密操作的核心对象
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] resultBytes = digest.digest(source.getBytes());
            for (byte resultByte : resultBytes) {
                //获取第四位值
                int lowValue = resultByte & 15 ;
                //右移四位和15 做与运算得到最高四位值
                int highValue = (resultByte >> 4) & 15;
                //以低四位，高四位的值作为下标从字符数组中获取对应字符
                char highCharacter = characters[highValue];
                char lowCharacter = characters[lowValue];
                builder.append(highCharacter).append(lowCharacter);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
