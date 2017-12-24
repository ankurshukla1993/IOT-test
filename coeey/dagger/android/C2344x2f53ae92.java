package dagger.android;

final class C2344x2f53ae92 implements ReleaseReferencesAt {
    private final int value;

    C2344x2f53ae92(int value) {
        this.value = value;
    }

    public Class<? extends ReleaseReferencesAt> annotationType() {
        return ReleaseReferencesAt.class;
    }

    public int value() {
        return this.value;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("@dagger.android.ReleaseReferencesAt(");
        sb.append(this.value);
        return sb.append(')').toString();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ReleaseReferencesAt)) {
            return false;
        }
        if (this.value != ((ReleaseReferencesAt) o).value()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return 1335633679 ^ this.value;
    }
}
