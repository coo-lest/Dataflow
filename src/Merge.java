public class Merge implements Actor {
    Channel[] channelsIn = new Channel[3];
    Channel[] channelsOut = new Channel[1];
    @Override
    public void connectIn(Channel c, int i) {
        channelsIn[i] = c;
    }

    @Override
    public void connectOut(Channel c, int i) {
        channelsOut[i] = c;
    }

    public boolean fire() {
        if ((channelsIn[0].isEmpty()) ||
                (channelsIn[0].peek() == 0 && channelsIn[2].isEmpty()) ||
                (channelsIn[0].peek() != 0 && channelsIn[1].isEmpty())) {
            return false;
        }
        if (channelsIn[0].receive() == 0) {
            // false
            channelsOut[0].send(channelsIn[2].receive());
        } else {
            // true
            channelsOut[0].send(channelsIn[1].receive());
        }
        return true;
    }
}
