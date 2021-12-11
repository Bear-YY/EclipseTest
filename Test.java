
public class Test {
	//ストライク判定
	//返り値 boolean
	static boolean checkStrike(int frame[]){
		boolean result;
		result = (frame[0] == 10) ? true : false;
		return result;
	}

	//スペア判定
	//返り値 boolean
	static boolean checkSpare(int frame[]){
		boolean result;
		result = ((frame[0] + frame[1] == 10) && !checkStrike(frame)) ? true : false;
		return result;
	}


	//1フレームで投げた回数
	//返り値 int
	static int countShot(int frame[]){
		if(frame.length == 3){
			if(checkSpare(frame) || (frame[0] + frame[1] == 20)){
				return 3;
			}else{
				return 2;
			}
		}else{
			if(checkStrike(frame)){
				return 1;
			}
			return 2;
		}
	}

	//1フレームの合計値計算
	//返り値 int
	static int addScoreFrame(int frame[]){
		int sumscore = 0;
		for(int i = 0; i < frame.length; i++ ){
			sumscore += frame[i];
		}
		return sumscore;
	}

	//スコアの配列を渡すと、投げた回数と投げた回数に応じた得点を入れた配列が取得できる
	//返り値 int[]
	static int[] setShotScore(int frames[][]){
		int sum = 0;
		int frame = 0;
		for(int i = 0;i < frames.length; i++){
			sum += countShot(frames[i]);
		}
		int[] shotscore = new int[sum];
		for(int i = 0; frame<shotscore.length; i++){
			for(int j = 0; j < countShot(frames[i]); j++){
				shotscore[frame] = frames[i][j];
				frame++;
			}
		}
		return shotscore;
	}


	public static void main(String[] args) {

		int[][] frames1 = {{6,4},{8,0},{10,0},{2,7},{5,5},{3,4},{10,0},{9,1},{1,2},{7,1,0}};
		//使う成績を入れた配列を設定
		int[][] frames = frames1;

		int[] shotscore = setShotScore(frames);
		//1投目が配列で0番目に当たるので、-1で初期化
		int nowshot = -1;
		int score = 0;

		for(int i = 0; i < frames.length; i++){
			nowshot += countShot(frames[i]);
			score += addScoreFrame(frames[i]);
			if(i < 9){
				if(checkStrike(frames[i])){
					score += shotscore[nowshot+1];
					score += shotscore[nowshot+2];
				}
				if(checkSpare(frames[i])){
					score += shotscore[nowshot+1];
				}
			}
			System.out.println((i+1)+"フレーム終了時:"+score);
		}
		System.out.println("TotalScore:"+score);

	}

}
