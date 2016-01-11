package separate;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
/*
 * 
 * ��ԭ�ı������Ա�ע��
 * �����ʽΪ��The list of prisoners who may be
 * �õ��Ľ����ʽΪ��The/DT list/NN of/IN prisoners/NNS who/WP may/MD be/VB
 */
public class SeparateWord {
	public String SeparateWords(String Line) {
		String str = Line;// StringBuffer buf = new StringBuffer();
		String result = "";
//		 String str =
//		 "The list of prisoners who may be released in coming days includes militants"
//		 +
//		 " who threw firebombs, in one case at a bus carrying children; stabbed and shot"
//		 +
//		 " civilians, including women, elderly Jews and suspected Palestinian collaborators; "
//		 +
//		 "and ambushed and killed border guards, police officers, security agents and soldiers. "
//		 +
//		 "All of them have been in prison for at least two decades; some were serving life sentences.";
		MaxentTagger tagger = new MaxentTagger(
				"C:/Users/Luojw/Documents/nlp/stanford-postagger-full-2015-04-20/models/chinese-distsim.tagger");
		Long start = System.currentTimeMillis();
		//�ִ�
		List<List<HasWord>> sentences = MaxentTagger
				.tokenizeText(new StringReader(str));
		System.out.println("Tagging " + (System.currentTimeMillis() - start)
				+ "��秒");
				//�ִʽ�����
		for (List<HasWord> sentence : sentences) {
			ArrayList<TaggedWord> tSentence = (ArrayList<TaggedWord>) tagger.tagSentence(sentence);
			result = result + "" + Sentence.listToString(tSentence, false);
			System.out.println(Sentence.listToString(tSentence, false));
		}
		return result;

	}

	
}