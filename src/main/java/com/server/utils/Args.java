package com.server.utils;

import com.google.common.base.Strings;

public class Args
{
	/**
	 * 判断字符串参数是否为空
	 * @param arg
	 * @return
	 */
    public static boolean isEmpty(String arg)
    {
        return Strings.isNullOrEmpty(arg);
    }

    /**
     * 判断object是否为空
     * @param arg
     * @return
     */
    public static boolean isNull(Object arg)
    {
		return arg == null;
    }
}
