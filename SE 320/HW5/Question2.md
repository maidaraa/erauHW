Write a precondition or required clause for the method removeDuplicates, so all duplicates from List lst are removed.

public static void removeDuplicates(List lst) {
if (lst == null || lst.size() == 0) return;
List copy = new ArrayList(lst);
Iterator elements = copy.iterator();
Object pre = elements.next();
while(elements.hasNext()) {
Object nex = elements.next();
if (pre.equals(nex)) lst.remove(nex);
else pre = nex;
}
}

Precondition:

- list must not be null or of size 0
- list must allow for removing all duplicates 
