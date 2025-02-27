/*
 * JBoss, Home of Professional Open Source.
 *
 * Copyright 2022 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.logmanager.formatters;

import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.Formattable;
import java.util.Locale;
import java.util.UUID;

class ColorPrintf extends Printf {
    private final int darken;

    ColorPrintf(final int darken) {
        super(Locale.getDefault());
        this.darken = darken;
    }

    public StringBuilder formatDirect(final StringBuilder destination, final String format, final Object... params) {
        ColorUtil.endFgColor(destination);
        super.formatDirect(destination, format, params);
        return destination;
    }

    protected void formatTimeTextField(final StringBuilder target, final TemporalAccessor ta, final TemporalField field, final String[] symbols, final GeneralFlags genFlags, final int width) {
        super.formatTimeTextField(target, ta, field, symbols, genFlags, width);
    }

    protected void formatTimeZoneId(final StringBuilder target, final TemporalAccessor ta, final GeneralFlags genFlags, final int width) {
        super.formatTimeZoneId(target, ta, genFlags, width);
    }

    protected void formatTimeZoneOffset(final StringBuilder target, final TemporalAccessor ta, final GeneralFlags genFlags, final int width) {
        super.formatTimeZoneOffset(target, ta, genFlags, width);
    }

    protected void formatTimeField(final StringBuilder target, final TemporalAccessor ta, final TemporalField field, final GeneralFlags genFlags, final int width, final int zeroPad) {
        super.formatTimeField(target, ta, field, genFlags, width, zeroPad);
    }


    protected void formatPercent(final StringBuilder target) {
        super.formatPercent(target);
    }

    protected void formatLineSeparator(final StringBuilder target) {
        super.formatLineSeparator(target);
    }

    protected void formatFormattableString(final StringBuilder target, final Formattable formattable, final GeneralFlags genFlags, final int width, final int precision) {
        super.formatFormattableString(target, formattable, genFlags, width, precision);
    }

    protected void formatPlainString(final StringBuilder target, final Object item, final GeneralFlags genFlags, final int width, final int precision) {
        if (item instanceof Class || item instanceof Executable || item instanceof Field) {
            ColorUtil.startFgColor(target, ColorPatternFormatter.trueColor, 0xff >>> darken, 0xff >>> darken, 0xdd >>> darken);
        } else if (item instanceof UUID) {
            ColorUtil.startFgColor(target, ColorPatternFormatter.trueColor, 0xdd >>> darken, 0xff >>> darken, 0xdd >>> darken);
        } else {
            ColorUtil.startFgColor(target, ColorPatternFormatter.trueColor, 0xdd >>> darken, 0xdd >>> darken, 0xdd >>> darken);
        }
        super.formatPlainString(target, item, genFlags, width, precision);
        ColorUtil.endFgColor(target);
    }

    protected void formatBoolean(final StringBuilder target, final Object item, final GeneralFlags genFlags, final int width, final int precision) {
        super.formatBoolean(target, item, genFlags, width, precision);
    }

    protected void formatHashCode(final StringBuilder target, final Object item, final GeneralFlags genFlags, final int width, final int precision) {
        super.formatHashCode(target, item, genFlags, width, precision);
    }

    protected void formatCharacter(final StringBuilder target, final int codePoint, final GeneralFlags genFlags, final int width, final int precision) {
        super.formatCharacter(target, codePoint, genFlags, width, precision);
    }

    protected void formatDecimalInteger(final StringBuilder target, final Number item, final GeneralFlags genFlags, final NumericFlags numFlags, final int width) {
        super.formatDecimalInteger(target, item, genFlags, numFlags, width);
    }

    protected void formatOctalInteger(final StringBuilder target, final Number item, final GeneralFlags genFlags, final NumericFlags numFlags, final int width) {
        super.formatOctalInteger(target, item, genFlags, numFlags, width);
    }

    protected void formatHexInteger(final StringBuilder target, final Number item, final GeneralFlags genFlags, final NumericFlags numFlags, final int width) {
        super.formatHexInteger(target, item, genFlags, numFlags, width);
    }

    protected void formatFloatingPointSci(final StringBuilder target, final Number item, final GeneralFlags genFlags, final NumericFlags numFlags, final int width, final int precision) {
        super.formatFloatingPointSci(target, item, genFlags, numFlags, width, precision);
    }

    protected void formatFloatingPointDecimal(final StringBuilder target, final Number item, final GeneralFlags genFlags, final NumericFlags numFlags, final int width, final int precision) {
        super.formatFloatingPointDecimal(target, item, genFlags, numFlags, width, precision);
    }

    protected void formatFloatingPointGeneral(final StringBuilder target, final Number item, final GeneralFlags genFlags, final NumericFlags numFlags, final int width, final int precision) {
        super.formatFloatingPointGeneral(target, item, genFlags, numFlags, width, precision);
    }
}
