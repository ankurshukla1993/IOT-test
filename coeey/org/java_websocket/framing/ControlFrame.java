package org.java_websocket.framing;

import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.framing.Framedata.Opcode;

public abstract class ControlFrame extends FramedataImpl1 {
    public ControlFrame(Opcode opcode) {
        super(opcode);
    }

    public void isValid() throws InvalidDataException {
        if (!isFin()) {
            throw new InvalidFrameException("Control frame cant have fin==false set");
        } else if (isRSV1()) {
            throw new InvalidFrameException("Control frame cant have rsv1==true set");
        } else if (isRSV2()) {
            throw new InvalidFrameException("Control frame cant have rsv2==true set");
        } else if (isRSV3()) {
            throw new InvalidFrameException("Control frame cant have rsv3==true set");
        }
    }
}
