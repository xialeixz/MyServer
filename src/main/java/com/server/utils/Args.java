package com.server.utils;

import com.google.common.base.Strings;

public class Args
{
	/**
	 * �ж��ַ��������Ƿ�Ϊ��
	 * @param arg
	 * @return
	 */
    public static boolean isEmpty(String arg)
    {
        return Strings.isNullOrEmpty(arg);
    }

    /**
     * �ж�object�Ƿ�Ϊ��
     * @param arg
     * @return
     */
    public static boolean isNull(Object arg)
    {
		return arg == null;
    }
}
