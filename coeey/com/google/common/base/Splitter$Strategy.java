package com.google.common.base;

import java.util.Iterator;

interface Splitter$Strategy {
    Iterator<String> iterator(Splitter splitter, CharSequence charSequence);
}
