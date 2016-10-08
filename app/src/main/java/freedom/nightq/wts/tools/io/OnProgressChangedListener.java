package freedom.nightq.wts.tools.io;

public interface OnProgressChangedListener {
    void onChange(long total, long current);

    /**
     * 可能多次调用
     *
     * @param error
     * @param msg
     */
    void onError(Exception error, String msg);

}
