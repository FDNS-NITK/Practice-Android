package flappybird.failedcoder.com.demo2;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {
    private AudioAttributes audioAttributes;
    final int SOUND_POOL_MAX=2;
    private static SoundPool soundPool;
    private static int hitSound;
    private static int oversound;

    public SoundPlayer(Context context)
    {
        soundPool=new SoundPool(SOUND_POOL_MAX, AudioManager.STREAM_MUSIC,0);
        hitSound=soundPool.load(context,R.raw.hit,1);
        oversound=soundPool.load(context,R.raw.explosion,1);
    }
    public void playHitSound(){
        soundPool.play(hitSound,1.0f,1.0f,1,0,1.0f);
    }
    public void playOverSound(){
        soundPool.play(oversound,1.0f,1.0f,1,0,1.0f);
    }
}
