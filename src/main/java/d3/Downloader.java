package d3;

import java.util.concurrent.CopyOnWriteArrayList;

public class Downloader {
    interface ProgressListener {
        void onProgress(int n);
    }

    private CopyOnWriteArrayList<ProgressListener> listeners;

    public void addListener(ProgressListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ProgressListener listener) {
        listeners.remove(listener);
    }

    private void updateProgress(int n) {
        for (ProgressListener listener : listeners) {
            listener.onProgress(n);
        }
    }
}
