import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ScratchPad {
/**
 * givevn a string, count of total capitala letters where it should 
 * @param args
 */
	public static void main(String[] args) {
		String x="abcAxyzBb gGAAA";

		List<Character> upcaseCharacters = x.chars().filter(Character::isUpperCase).mapToObj(s->(char)s).collect(Collectors.toList());
		List<Character> lst=new ArrayList<>();
		for(var c:upcaseCharacters) {
			int pos=x.indexOf(c);
			int lastIndexOfSmallLetter=x.lastIndexOf(Character.toLowerCase(c));
			if(lastIndexOfSmallLetter>=0 && lastIndexOfSmallLetter<pos) {
				lst.add(c);
			}
		}
		System.out.println(lst+"  "+lst.size());
	}

}
