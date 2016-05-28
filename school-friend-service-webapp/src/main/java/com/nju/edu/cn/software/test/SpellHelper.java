package com.nju.edu.cn.software.test;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class SpellHelper {

	//The Chinese converted to English
    @SuppressWarnings("deprecation")
	public static String getEname(String name) throws BadHanyuPinyinOutputFormatCombination {
    	//First need to create the HanyuPinyinOutputFormat formatting objects
        HanyuPinyinOutputFormat pyFormat = new HanyuPinyinOutputFormat();
        //Set the size of the write format
        //outputFormat.setCaseType(HanyuPinyinCaseType);
        //HanyuPinyinCaseType.LOWERCASE converted to lowercase mode output
        //HanyuPinyinCaseType.UPPERCASE converted to all uppercase output
        pyFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        //Method of parameter HanyuPinyinToneType has the following constant object: 
        //HanyuPinyinToneType.WITH_TONE_NUMBER is expressed numerically tone, for example: zhao4
        //HanyuPinyinToneType.WITHOUT_TONE toneless said, for example: zhao
        //HanyuPinyinToneType.WITH_TONE_MARK said with a tone symbol, for example: zhao
        pyFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        
        //Method of parameter HanyuPinyinVCharType has the following constant object: 
        //HanyuPinyinVCharType.WITH_U_AND_COLON to U and a colon says the Pinyin,  
        //HanyuPinyinVCharType.WITH_V to V to represent the character,  
        //HanyuPinyinVCharType.WITH_U_UNICODE  
        pyFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

     return PinyinHelper.toHanyuPinyinString(name, pyFormat,"");
    }
    public static void main(String[] args) {
        try {
			System.out.println(getEname("兰州理工大学"));
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    
    
}
