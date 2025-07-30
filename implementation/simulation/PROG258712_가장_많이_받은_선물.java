import java.util.*;

class Solution {
    static class GiftStatus {
        int[] giving;

        GiftStatus(int n) {
            giving = new int[n];
        }

        void giveTo(int to) {
            giving[to]++;
        }

        int totalGiven() {
            int sum = 0;
            for (int val : giving) sum += val;
            return sum;
        }

        int totalReceivedFrom(GiftStatus[] allStatuses, int myIndex) {
            int sum = 0;
            for (GiftStatus status : allStatuses) {
                sum += status.giving[myIndex];
            }
            return sum;
        }

        int getGiftScore(GiftStatus[] allStatuses, int myIndex) {
            return totalGiven() - totalReceivedFrom(allStatuses, myIndex);
        }
    }

    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        Map<String, Integer> nameToIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nameToIndex.put(friends[i], i);
        }

        GiftStatus[] giftStatus = new GiftStatus[n];
        for (int i = 0; i < n; i++) {
            giftStatus[i] = new GiftStatus(n);
        }

        for (String gift : gifts) {
            String[] parts = gift.split(" ");
            int from = nameToIndex.get(parts[0]);
            int to = nameToIndex.get(parts[1]);
            giftStatus[from].giveTo(to);
        }

        int[] nextMonthReceived = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                int given = giftStatus[i].giving[j];
                int received = giftStatus[j].giving[i];

                if (given > received) {
                    nextMonthReceived[i]++;
                } else if (given == received) {
                    int scoreI = giftStatus[i].getGiftScore(giftStatus, i);
                    int scoreJ = giftStatus[j].getGiftScore(giftStatus, j);
                    if (scoreI > scoreJ) {
                        nextMonthReceived[i]++;
                    }
                }
            }
        }

        int max = 0;
        for (int val : nextMonthReceived) {
            max = Math.max(max, val);
        }

        return max;
    }
}
