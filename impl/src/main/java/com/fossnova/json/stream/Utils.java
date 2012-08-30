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
package com.fossnova.json.stream;

import static com.fossnova.json.stream.JsonConstants.BACKSLASH;

/**
 * @author <a href="mailto:opalka dot richard at gmail dot com">Richard Opalka</a>
 */
final class Utils {

    private Utils() {
        // forbidden instantiation
    }

    static boolean isControl( final int c ) {
        // ASCII control characters
        if ( ( ( c >= '\u0000' ) && ( c <= '\u001F' ) ) || ( c == '\u007F' ) ) {
            return true;
        }
        // ISO-8859 control characters
        if ( ( c >= '\u0080' ) && ( c <= '\u009F' ) ) {
            return true;
        }
        // not unicode control character
        return false;
    }

    static boolean isWhitespace( final int c ) {
        return ( c == ' ' ) || ( c == '\t' ) || ( c == '\r' ) || ( c == '\n' );
    }

    static String toUnicodeString( final int c ) {
        final StringBuilder sb = new StringBuilder();
        sb.append( BACKSLASH ).append( 'u' );
        final String hexString = Integer.toHexString( c );
        for ( int j = 0; j < ( 4 - hexString.length() ); j++ ) {
            sb.append( '0' );
        }
        sb.append( hexString.toUpperCase() );
        return sb.toString();
    }
}
