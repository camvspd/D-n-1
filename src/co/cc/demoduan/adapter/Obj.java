package co.cc.demoduan.adapter;

public class Obj {
	private String _id;
	private String word_key;
	private String word_meaning;
	private byte[] word_image;

	public Obj() {

	}

	public Obj(String _id, String word_key, String word_meaning,
			byte[] word_image) {
		this._id = _id;
		this.word_key = word_key;
		this.word_meaning = word_meaning;
		this.word_image = word_image;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getWord_key() {
		return word_key;
	}

	public void setWord_key(String word_key) {
		this.word_key = word_key;
	}

	public String getWord_meaning() {
		return word_meaning;
	}

	public void setWord_meaning(String word_meaning) {
		this.word_meaning = word_meaning;
	}

	public byte[] getWord_image() {
		return word_image;
	}

	public void setWord_image(byte[] word_image) {
		this.word_image = word_image;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return word_meaning;
	}
	
	
}
