/*
 * Copyright (c) 2012, FOSS Nova Software foundation (FNSF),
 * and individual contributors as indicated by the @author tags.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package test.fossnova.json;

import static org.fossnova.json.JsonEvent.ARRAY_END;
import static org.fossnova.json.JsonEvent.ARRAY_START;
import static org.fossnova.json.JsonEvent.BOOLEAN;
import static org.fossnova.json.JsonEvent.NULL;
import static org.fossnova.json.JsonEvent.NUMBER;
import static org.fossnova.json.JsonEvent.OBJECT_END;
import static org.fossnova.json.JsonEvent.OBJECT_START;
import static org.fossnova.json.JsonEvent.STRING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.fossnova.json.JsonReader;

/**
 * @author <a href="mailto:opalka dot richard at gmail dot com">Richard Opalka</a>
 */
abstract class AbstractJsonTestCase {

    // TODO: validate e.g. getBoolean() returns exception when isBoolean() returns false
    // TODO: generalization: validate get methods return appropriate exception if in wrong state
    static void assertObjectStartState( final JsonReader reader ) throws IOException {
        assertTrue( reader.hasNext() );
        assertEquals( OBJECT_START, reader.next() );
        assertTrue( reader.isObjectStart() );
        assertFalse( reader.isObjectEnd() );
        assertFalse( reader.isArrayStart() );
        assertFalse( reader.isArrayEnd() );
        assertFalse( reader.isNull() );
        assertFalse( reader.isBoolean() );
        assertFalse( reader.isNumber() );
        assertFalse( reader.isString() );
    }

    static void assertObjectEndState( final JsonReader reader ) throws IOException {
        assertTrue( reader.hasNext() );
        assertEquals( OBJECT_END, reader.next() );
        assertFalse( reader.isObjectStart() );
        assertTrue( reader.isObjectEnd() );
        assertFalse( reader.isArrayStart() );
        assertFalse( reader.isArrayEnd() );
        assertFalse( reader.isNull() );
        assertFalse( reader.isBoolean() );
        assertFalse( reader.isNumber() );
        assertFalse( reader.isString() );
    }

    static void assertArrayStartState( final JsonReader reader ) throws IOException {
        assertTrue( reader.hasNext() );
        assertEquals( ARRAY_START, reader.next() );
        assertFalse( reader.isObjectStart() );
        assertFalse( reader.isObjectEnd() );
        assertTrue( reader.isArrayStart() );
        assertFalse( reader.isArrayEnd() );
        assertFalse( reader.isNull() );
        assertFalse( reader.isBoolean() );
        assertFalse( reader.isNumber() );
        assertFalse( reader.isString() );
    }

    static void assertArrayEndState( final JsonReader reader ) throws IOException {
        assertTrue( reader.hasNext() );
        assertEquals( ARRAY_END, reader.next() );
        assertFalse( reader.isObjectStart() );
        assertFalse( reader.isObjectEnd() );
        assertFalse( reader.isArrayStart() );
        assertTrue( reader.isArrayEnd() );
        assertFalse( reader.isNull() );
        assertFalse( reader.isBoolean() );
        assertFalse( reader.isNumber() );
        assertFalse( reader.isString() );
    }

    static void assertFinalState( final JsonReader reader ) throws IOException {
        assertFalse( reader.hasNext() );
    }

    static void assertStringState( final JsonReader reader, final String expected ) throws IOException {
        assertTrue( reader.hasNext() );
        assertEquals( STRING, reader.next() );
        assertFalse( reader.isObjectStart() );
        assertFalse( reader.isObjectEnd() );
        assertFalse( reader.isArrayStart() );
        assertFalse( reader.isArrayEnd() );
        assertFalse( reader.isNull() );
        assertFalse( reader.isBoolean() );
        assertFalse( reader.isNumber() );
        assertTrue( reader.isString() );
        assertEquals( expected, reader.getString() );
    }

    static void assertByteState( final JsonReader reader, final byte expected ) throws IOException {
        assertNumberState( reader );
        assertEquals( expected, reader.getByte() );
    }

    static void assertShortState( final JsonReader reader, final short expected ) throws IOException {
        assertNumberState( reader );
        assertEquals( expected, reader.getShort() );
    }

    static void assertIntState( final JsonReader reader, final int expected ) throws IOException {
        assertNumberState( reader );
        assertEquals( expected, reader.getInt() );
    }

    static void assertLongState( final JsonReader reader, final long expected ) throws IOException {
        assertNumberState( reader );
        assertEquals( expected, reader.getLong() );
    }

    static void assertFloatState( final JsonReader reader, final float expected ) throws IOException {
        assertNumberState( reader );
        assertTrue( Math.abs( reader.getFloat() - expected ) <= 0.0000001 );
    }

    static void assertDoubleState( final JsonReader reader, final double expected ) throws IOException {
        assertNumberState( reader );
        assertTrue( Math.abs( reader.getDouble() - expected ) <= 0.0000001 );
    }

    static void assertBooleanState( final JsonReader reader, final boolean expected ) throws IOException {
        assertTrue( reader.hasNext() );
        assertEquals( BOOLEAN, reader.next() );
        assertFalse( reader.isObjectStart() );
        assertFalse( reader.isObjectEnd() );
        assertFalse( reader.isArrayStart() );
        assertFalse( reader.isArrayEnd() );
        assertFalse( reader.isNull() );
        assertTrue( reader.isBoolean() );
        assertFalse( reader.isNumber() );
        assertFalse( reader.isString() );
        assertEquals( expected, reader.getBoolean() );
    }

    static void assertNullState( final JsonReader reader ) throws IOException {
        assertTrue( reader.hasNext() );
        assertEquals( NULL, reader.next() );
        assertFalse( reader.isObjectStart() );
        assertFalse( reader.isObjectEnd() );
        assertFalse( reader.isArrayStart() );
        assertFalse( reader.isArrayEnd() );
        assertTrue( reader.isNull() );
        assertFalse( reader.isBoolean() );
        assertFalse( reader.isNumber() );
        assertFalse( reader.isString() );
    }

    private static void assertNumberState( final JsonReader reader ) throws IOException {
        assertTrue( reader.hasNext() );
        assertEquals( NUMBER, reader.next() );
        assertFalse( reader.isObjectStart() );
        assertFalse( reader.isObjectEnd() );
        assertFalse( reader.isArrayStart() );
        assertFalse( reader.isArrayEnd() );
        assertFalse( reader.isNull() );
        assertFalse( reader.isBoolean() );
        assertTrue( reader.isNumber() );
        assertFalse( reader.isString() );
    }
}