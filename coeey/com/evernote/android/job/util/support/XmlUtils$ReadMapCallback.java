package com.evernote.android.job.util.support;

import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public interface XmlUtils$ReadMapCallback {
    Object readThisUnknownObjectXml(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException;
}
