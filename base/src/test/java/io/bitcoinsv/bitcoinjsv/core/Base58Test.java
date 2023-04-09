/*
 * Copyright 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.bitcoinsv.bitcoinjsv.core;

import io.bitcoinsv.bitcoinjsv.exception.AddressFormatException;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


public class Base58Test {
    @Test
    public void testEncode() {
        byte[] testBytes = "Hello World".getBytes();
        assertEquals("JxF12TrwUP45BMd", Base58.encode(testBytes));

        BigInteger bi = BigInteger.valueOf(3471844090L);
        assertEquals("16Ho7Hs", Base58.encode(bi.toByteArray()));

        byte[] zeroBytes1 = new byte[1];
        assertEquals("1", Base58.encode(zeroBytes1));

        byte[] zeroBytes7 = new byte[7];
        assertEquals("1111111", Base58.encode(zeroBytes7));

        // test empty encode
        assertEquals("", Base58.encode(new byte[0]));
    }

    @Test
    public void testDecode() {
        byte[] testBytes = "Hello World".getBytes();
        byte[] actualBytes = Base58.decode("JxF12TrwUP45BMd");
        assertTrue(Arrays.equals(testBytes, actualBytes), new String(actualBytes));

        assertTrue(Arrays.equals(Base58.decode("1"), new byte[1]), "1");
        assertTrue(Arrays.equals(Base58.decode("1111"), new byte[4]), "1111");

        try {
            Base58.decode("This isn't valid base58");
            fail();
        } catch (AddressFormatException e) {
            // expected
        }

        Base58.decodeChecked("4stwEBjT6FYyVV");

        // Checksum should fail.
        try {
            Base58.decodeChecked("4stwEBjT6FYyVW");
            fail();
        } catch (AddressFormatException e) {
            // expected
        }

        // Input is too short.
        try {
            Base58.decodeChecked("4s");
            fail();
        } catch (AddressFormatException e) {
            // expected
        }

        // Test decode of empty String.
        assertEquals(0, Base58.decode("").length);

        // Now check we can correctly decode the case where the high bit of the first byte is not zero, so BigInteger
        // sign extends. Fix for a bug that stopped us parsing keys exported using sipas patch.
        Base58.decodeChecked("93VYUMzRG9DdbRP72uQXjaWibbQwygnvaCu9DumcqDjGybD864T");
    }

    @Test
    public void testDecodeToBigInteger() {
        byte[] input = Base58.decode("129");
        assertEquals(new BigInteger(1, input), Base58.decodeToBigInteger("129"));
    }
}