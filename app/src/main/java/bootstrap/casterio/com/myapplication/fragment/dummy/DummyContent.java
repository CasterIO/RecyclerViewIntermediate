package bootstrap.casterio.com.myapplication.fragment.dummy;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    public static final List<DummyItem> ITEMS_Y1 = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    public static final int DUMMY_COUNT = 10;

    static {
        // Add some sample items.
        for (int i = 1; i <= DUMMY_COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static List<DummyItem> shuffle(List<DummyItem> items) {
        List<DummyItem> newItems = new ArrayList<>();
        newItems.addAll(items);
        Collections.shuffle(newItems);
        return newItems;
    }

    public static List<DummyItem> sort(List<DummyItem> items) {
        List<DummyItem> newItems = new ArrayList<>();
        newItems.addAll(items);
        Collections.sort(newItems);
        return newItems;
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(Long.toString(item.id), item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(position, makeDetails(position, false), makeDetails(position, false));
    }

    public static DummyItem createDummyItemY(int position) {
        return new DummyItem(position, makeDetails(position, true), makeDetails(position, true));
    }

    private static String makeDetails(int position, boolean isY) {
        StringBuffer retString = new StringBuffer();
        retString.append("Item: " + position + "\n");

        boolean FIXED_MODE = false;
        if (FIXED_MODE) {
            if (isY) {
                retString.append("YYYYYYYY");
            } else {
                retString.append("XXXXXXXX");
            }
        } else {
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(100);
            for (int x = 0; x < randomInt; x++) {
                if (isY) {
                    retString.append("Y");
                } else {
                    retString.append("X");
                }


            }
        }
        return retString.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem implements Comparable<DummyItem>{
        public final long id;
        public final String content;
        public final String details;

        public DummyItem(long id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content + Long.toString(id) + details;
        }

        @Override
        public boolean equals(Object o) {
            // self check
            if (this == o)
                return true;
            // null check
            if (o == null)
                return false;
            // type check and cast
            if (getClass() != o.getClass())
                return false;
            DummyItem dummyItem = (DummyItem) o;
            // field comparison
            return Objects.equals(content, dummyItem.content)
                    && Objects.equals(details, dummyItem.details);
        }


        @Override
        public int hashCode() {
            return (int) id;
        }

        @Override
        public int compareTo(@NonNull DummyItem dummyItem) {
            return Long.compare(id, dummyItem.id);
        }
    }
}
