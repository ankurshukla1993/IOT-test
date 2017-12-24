package org.webrtc;

import java.util.LinkedList;
import org.webrtc.MediaStreamTrack.MediaType;

public class RtpParameters {
    public final LinkedList<Codec> codecs = new LinkedList();
    public final LinkedList<Encoding> encodings = new LinkedList();

    public static class Codec {
        public Integer clockRate;
        MediaType kind;
        public String name;
        public Integer numChannels;
        public int payloadType;
    }

    public static class Encoding {
        public boolean active = true;
        public Integer maxBitrateBps;
        public Long ssrc;
    }
}
