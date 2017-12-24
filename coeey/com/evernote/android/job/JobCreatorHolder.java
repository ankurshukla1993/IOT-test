package com.evernote.android.job;

import com.evernote.android.job.util.JobCat;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.vrallev.android.cat.CatLog;

class JobCreatorHolder {
    private static final CatLog CAT = new JobCat("JobCreatorHolder");
    private final List<JobCreator> mJobCreators = new CopyOnWriteArrayList();

    public void addJobCreator(JobCreator creator) {
        this.mJobCreators.add(creator);
    }

    public void removeJobCreator(JobCreator creator) {
        this.mJobCreators.remove(creator);
    }

    public Job createJob(String tag) {
        Job job = null;
        boolean atLeastOneCreatorSeen = false;
        for (JobCreator jobCreator : this.mJobCreators) {
            atLeastOneCreatorSeen = true;
            job = jobCreator.create(tag);
            if (job != null) {
                break;
            }
        }
        if (!atLeastOneCreatorSeen) {
            CAT.w("no JobCreator added");
        }
        return job;
    }

    public boolean isEmpty() {
        return this.mJobCreators.isEmpty();
    }
}
