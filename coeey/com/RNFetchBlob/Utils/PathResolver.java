package com.RNFetchBlob.Utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import com.RNFetchBlob.RNFetchBlobUtils;
import com.facebook.common.util.UriUtil;
import com.facebook.internal.AnalyticsEvents;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class PathResolver {
    public static java.lang.String getDataColumn(android.content.Context r12, android.net.Uri r13, java.lang.String r14, java.lang.String[] r15) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x003b in list []
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r11 = 0;
        r7 = 0;
        r10 = 0;
        r6 = "_data";
        r0 = 1;
        r2 = new java.lang.String[r0];
        r0 = 0;
        r1 = "_data";
        r2[r0] = r1;
        r0 = r12.getContentResolver();	 Catch:{ Exception -> 0x0032, all -> 0x003d }
        r5 = 0;	 Catch:{ Exception -> 0x0032, all -> 0x003d }
        r1 = r13;	 Catch:{ Exception -> 0x0032, all -> 0x003d }
        r3 = r14;	 Catch:{ Exception -> 0x0032, all -> 0x003d }
        r4 = r15;	 Catch:{ Exception -> 0x0032, all -> 0x003d }
        r7 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0032, all -> 0x003d }
        if (r7 == 0) goto L_0x002b;	 Catch:{ Exception -> 0x0032, all -> 0x003d }
    L_0x001b:
        r0 = r7.moveToFirst();	 Catch:{ Exception -> 0x0032, all -> 0x003d }
        if (r0 == 0) goto L_0x002b;	 Catch:{ Exception -> 0x0032, all -> 0x003d }
    L_0x0021:
        r0 = "_data";	 Catch:{ Exception -> 0x0032, all -> 0x003d }
        r9 = r7.getColumnIndexOrThrow(r0);	 Catch:{ Exception -> 0x0032, all -> 0x003d }
        r10 = r7.getString(r9);	 Catch:{ Exception -> 0x0032, all -> 0x003d }
    L_0x002b:
        if (r7 == 0) goto L_0x0030;
    L_0x002d:
        r7.close();
    L_0x0030:
        r0 = r10;
    L_0x0031:
        return r0;
    L_0x0032:
        r8 = move-exception;
        r8.printStackTrace();	 Catch:{ Exception -> 0x0032, all -> 0x003d }
        if (r7 == 0) goto L_0x003b;
    L_0x0038:
        r7.close();
    L_0x003b:
        r0 = r11;
        goto L_0x0031;
    L_0x003d:
        r0 = move-exception;
        if (r7 == 0) goto L_0x0043;
    L_0x0040:
        r7.close();
    L_0x0043:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.RNFetchBlob.Utils.PathResolver.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    public static String getRealPathFromURI(Context context, Uri uri) {
        if ((VERSION.SDK_INT >= 19) && DocumentsContract.isDocumentUri(context, uri)) {
            String[] split;
            if (isExternalStorageDocument(uri)) {
                split = DocumentsContract.getDocumentId(uri).split(":");
                if ("primary".equalsIgnoreCase(split[0])) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
            } else if (isMediaDocument(uri)) {
                String type = DocumentsContract.getDocumentId(uri).split(":")[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = Media.EXTERNAL_CONTENT_URI;
                } else if (AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO.equals(type)) {
                    contentUri = Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = "_id=?";
                return getDataColumn(context, contentUri, "_id=?", new String[]{split[1]});
            } else if (!"content".equalsIgnoreCase(uri.getScheme())) {
                try {
                    InputStream attachment = context.getContentResolver().openInputStream(uri);
                    if (attachment != null) {
                        String filename = getContentName(context.getContentResolver(), uri);
                        if (filename != null) {
                            File file = new File(context.getCacheDir(), filename);
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            byte[] buffer = new byte[1024];
                            while (attachment.read(buffer) > 0) {
                                fileOutputStream.write(buffer);
                            }
                            fileOutputStream.close();
                            attachment.close();
                            return file.getAbsolutePath();
                        }
                    }
                } catch (Exception e) {
                    RNFetchBlobUtils.emitWarningEvent(e.toString());
                    return null;
                }
            } else if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            } else {
                return getDataColumn(context, uri, null, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }
            return getDataColumn(context, uri, null, null);
        } else if (UriUtil.LOCAL_FILE_SCHEME.equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    private static String getContentName(ContentResolver resolver, Uri uri) {
        Cursor cursor = resolver.query(uri, null, null, null, null);
        cursor.moveToFirst();
        int nameIndex = cursor.getColumnIndex("_display_name");
        if (nameIndex < 0) {
            return null;
        }
        String name = cursor.getString(nameIndex);
        cursor.close();
        return name;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
