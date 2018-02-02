package com.sven.facialid.util;

import java.io.IOException;
import java.net.URLDecoder;

import org.apache.tomcat.util.codec.binary.Base64;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils
{
    public static <T> T fromBase64(final String rawBase64String, final Class<T> t)
    {

        try
        {
            String base64String = URLDecoder.decode(rawBase64String, "utf-8");

            String json = new String(Base64.decodeBase64(base64String));
            return new ObjectMapper().readValue(json, t);
        }
        catch (JsonParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (JsonMappingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static <T> String toBase64(final T t)
    {

        String json = "";
        try
        {
            json = new ObjectMapper().writeValueAsString(t);
        }
        catch (JsonProcessingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(json.getBytes()));
    }
}
