package com.cooey.common.realm_store;

import android.content.Context;
import com.cooey.common.vo.ProtoRealm;
import com.cooey.common.vo.careplan.Course;
import com.facebook.share.internal.ShareConstants;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.util.List;

public class CourseStore {
    private Realm realm;

    public CourseStore(Context context) {
        this.realm = ProtoRealm.getInstance(context).getRealm();
    }

    public CourseStore(Realm realm) {
        this.realm = realm;
    }

    public static CourseStore getInstance(Context context) {
        return new CourseStore(context);
    }

    public void writeToCourse(final Course course) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(course);
            }
        });
    }

    public void writeToCourse(final List<Course> courses) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(courses);
            }
        });
    }

    public Course getCourse(String id) {
        return (Course) this.realm.where(Course.class).equalTo(ShareConstants.WEB_DIALOG_PARAM_ID, id).findFirst();
    }

    public int getCourseCount() {
        return this.realm.where(Course.class).findAll().size();
    }
}
