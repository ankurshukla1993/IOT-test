package com.google.protobuf;

import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

public abstract class AbstractMessageLite implements MessageLite {
    protected int memoizedHashCode = 0;

    public abstract class Builder implements com.google.protobuf.MessageLite.Builder {

        final class LimitedInputStream extends FilterInputStream {
            private int limit;

            LimitedInputStream(InputStream inputStream, int i) {
                super(inputStream);
                this.limit = i;
            }

            public int available() {
                return Math.min(super.available(), this.limit);
            }

            public int read() {
                if (this.limit <= 0) {
                    return -1;
                }
                int read = super.read();
                if (read < 0) {
                    return read;
                }
                this.limit--;
                return read;
            }

            public int read(byte[] bArr, int i, int i2) {
                if (this.limit <= 0) {
                    return -1;
                }
                int read = super.read(bArr, i, Math.min(i2, this.limit));
                if (read < 0) {
                    return read;
                }
                this.limit -= read;
                return read;
            }

            public long skip(long j) {
                long skip = super.skip(Math.min(j, (long) this.limit));
                if (skip >= 0) {
                    this.limit = (int) (((long) this.limit) - skip);
                }
                return skip;
            }
        }

        protected static void addAll(Iterable iterable, Collection collection) {
            if (iterable instanceof LazyStringList) {
                checkForNullValues(((LazyStringList) iterable).getUnderlyingElements());
                collection.addAll((Collection) iterable);
            } else if (iterable instanceof Collection) {
                checkForNullValues(iterable);
                collection.addAll((Collection) iterable);
            } else {
                for (Object next : iterable) {
                    if (next == null) {
                        throw new NullPointerException();
                    }
                    collection.add(next);
                }
            }
        }

        private static void checkForNullValues(Iterable iterable) {
            for (Object obj : iterable) {
                if (obj == null) {
                    throw new NullPointerException();
                }
            }
        }

        protected static UninitializedMessageException newUninitializedMessageException(MessageLite messageLite) {
            return new UninitializedMessageException(messageLite);
        }

        public abstract Builder clone();

        public boolean mergeDelimitedFrom(InputStream inputStream) {
            return mergeDelimitedFrom(inputStream, ExtensionRegistryLite.getEmptyRegistry());
        }

        public boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            int read = inputStream.read();
            if (read == -1) {
                return false;
            }
            mergeFrom(new LimitedInputStream(inputStream, CodedInputStream.readRawVarint32(read, inputStream)), extensionRegistryLite);
            return true;
        }

        public Builder mergeFrom(ByteString byteString) {
            try {
                CodedInputStream newCodedInput = byteString.newCodedInput();
                mergeFrom(newCodedInput);
                newCodedInput.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (Throwable e2) {
                throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", e2);
            }
        }

        public Builder mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            try {
                CodedInputStream newCodedInput = byteString.newCodedInput();
                mergeFrom(newCodedInput, extensionRegistryLite);
                newCodedInput.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (Throwable e2) {
                throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", e2);
            }
        }

        public Builder mergeFrom(CodedInputStream codedInputStream) {
            return mergeFrom(codedInputStream, ExtensionRegistryLite.getEmptyRegistry());
        }

        public abstract Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite);

        public Builder mergeFrom(InputStream inputStream) {
            CodedInputStream newInstance = CodedInputStream.newInstance(inputStream);
            mergeFrom(newInstance);
            newInstance.checkLastTagWas(0);
            return this;
        }

        public Builder mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            CodedInputStream newInstance = CodedInputStream.newInstance(inputStream);
            mergeFrom(newInstance, extensionRegistryLite);
            newInstance.checkLastTagWas(0);
            return this;
        }

        public Builder mergeFrom(byte[] bArr) {
            return mergeFrom(bArr, 0, bArr.length);
        }

        public Builder mergeFrom(byte[] bArr, int i, int i2) {
            try {
                CodedInputStream newInstance = CodedInputStream.newInstance(bArr, i, i2);
                mergeFrom(newInstance);
                newInstance.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (Throwable e2) {
                throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
            }
        }

        public Builder mergeFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) {
            try {
                CodedInputStream newInstance = CodedInputStream.newInstance(bArr, i, i2);
                mergeFrom(newInstance, extensionRegistryLite);
                newInstance.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (Throwable e2) {
                throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
            }
        }

        public Builder mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return mergeFrom(bArr, 0, bArr.length, extensionRegistryLite);
        }
    }

    protected static void checkByteStringIsUtf8(ByteString byteString) {
        if (!byteString.isValidUtf8()) {
            throw new IllegalArgumentException("Byte string is not UTF-8.");
        }
    }

    UninitializedMessageException newUninitializedMessageException() {
        return new UninitializedMessageException((MessageLite) this);
    }

    public byte[] toByteArray() {
        try {
            byte[] bArr = new byte[getSerializedSize()];
            CodedOutputStream newInstance = CodedOutputStream.newInstance(bArr);
            writeTo(newInstance);
            newInstance.checkNoSpaceLeft();
            return bArr;
        } catch (Throwable e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public ByteString toByteString() {
        try {
            CodedBuilder newCodedBuilder = ByteString.newCodedBuilder(getSerializedSize());
            writeTo(newCodedBuilder.getCodedOutput());
            return newCodedBuilder.build();
        } catch (Throwable e) {
            throw new RuntimeException("Serializing to a ByteString threw an IOException (should never happen).", e);
        }
    }

    public void writeDelimitedTo(OutputStream outputStream) {
        int serializedSize = getSerializedSize();
        CodedOutputStream newInstance = CodedOutputStream.newInstance(outputStream, CodedOutputStream.computePreferredBufferSize(CodedOutputStream.computeRawVarint32Size(serializedSize) + serializedSize));
        newInstance.writeRawVarint32(serializedSize);
        writeTo(newInstance);
        newInstance.flush();
    }

    public void writeTo(OutputStream outputStream) {
        CodedOutputStream newInstance = CodedOutputStream.newInstance(outputStream, CodedOutputStream.computePreferredBufferSize(getSerializedSize()));
        writeTo(newInstance);
        newInstance.flush();
    }
}
