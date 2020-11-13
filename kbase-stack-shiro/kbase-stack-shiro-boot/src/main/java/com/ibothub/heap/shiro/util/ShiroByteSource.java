/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.util;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/11/13 21:21
 */
public class ShiroByteSource implements ByteSource, Serializable {

    private final byte[] bytes;
    private String cachedHex;
    private String cachedBase64;

    public ShiroByteSource(byte[] bytes) {
        this.bytes = bytes;
    }

    public ShiroByteSource(char[] chars) {
        this.bytes = CodecSupport.toBytes(chars);
    }

    public ShiroByteSource(String string) {
        this.bytes = CodecSupport.toBytes(string);
    }

    public ShiroByteSource(ByteSource source) {
        this.bytes = source.getBytes();
    }

    public ShiroByteSource(File file) {
        this.bytes = (new ShiroByteSource.BytesHelper()).getBytes(file);
    }

    public ShiroByteSource(InputStream stream) {
        this.bytes = (new ShiroByteSource.BytesHelper()).getBytes(stream);
    }

    public static boolean isCompatible(Object o) {
        return o instanceof byte[] || o instanceof char[] || o instanceof String || o instanceof ByteSource || o instanceof File || o instanceof InputStream;
    }

    public static final class Util {
        public Util() {
        }

        public static ByteSource bytes(byte[] bytes) {
            return new ShiroByteSource(bytes);
        }

        public static ByteSource bytes(char[] chars) {
            return new ShiroByteSource(chars);
        }

        public static ByteSource bytes(String string) {
            return new ShiroByteSource(string);
        }
    }

    @Override
    public byte[] getBytes() {
        return this.bytes;
    }

    @Override
    public boolean isEmpty() {
        return this.bytes == null || this.bytes.length == 0;
    }

    @Override
    public String toHex() {
        if (this.cachedHex == null) {
            this.cachedHex = Hex.encodeToString(this.getBytes());
        }

        return this.cachedHex;
    }

    @Override
    public String toBase64() {
        if (this.cachedBase64 == null) {
            this.cachedBase64 = Base64.encodeToString(this.getBytes());
        }

        return this.cachedBase64;
    }

    @Override
    public String toString() {
        return this.toBase64();
    }

    @Override
    public int hashCode() {
        return this.bytes != null && this.bytes.length != 0 ? Arrays.hashCode(this.bytes) : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof ByteSource) {
            ByteSource bs = (ByteSource)o;
            return Arrays.equals(this.getBytes(), bs.getBytes());
        } else {
            return false;
        }
    }

    private static final class BytesHelper extends CodecSupport {
        private BytesHelper() {
        }

        public byte[] getBytes(File file) {
            return this.toBytes(file);
        }

        public byte[] getBytes(InputStream stream) {
            return this.toBytes(stream);
        }
    }
}
