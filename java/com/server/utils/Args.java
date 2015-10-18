package com.server.utils;

import com.google.common.base.Strings;

public class Args
{
    public static boolean isEmpty(String arg)
    {
        return Strings.isNullOrEmpty(arg);
    }
}
