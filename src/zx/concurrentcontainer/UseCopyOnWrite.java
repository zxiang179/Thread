package zx.concurrentcontainer;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class UseCopyOnWrite {
	
	CopyOnWriteArrayList<String> cwal = new CopyOnWriteArrayList<String>();
	CopyOnWriteArraySet<String> cwas = new CopyOnWriteArraySet<String>();

}
