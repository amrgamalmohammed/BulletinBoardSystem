public class BulletInBoard {

    private int rSeq;
    private int sSeq;
    private int oVal;
    private int rNum;

    public BulletInBoard() {

        this.rSeq = 0;
        this.sSeq = 0;
        this.rNum = 0;
        this.oVal = -1;
    }

    public synchronized int getrSeq() {
        this.rSeq++;
        return this.rSeq;
    }

    public synchronized int getsSeq() {
        this.sSeq++;
        return this.sSeq;
    }

    public synchronized int getoVal() {
        return oVal;
    }

    public synchronized void setoVal(int oVal) {
        this.oVal = oVal;
    }

    public synchronized int getrNum() {
        this.rNum++;
        return this.rNum;
    }

    public synchronized void decrNum() {
        this.rNum--;
    }
}
