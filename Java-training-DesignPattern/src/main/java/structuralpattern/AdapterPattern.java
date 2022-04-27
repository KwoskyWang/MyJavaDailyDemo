package structuralpattern;

/**
 * 适配器模式
 *
 * 使用场景：系统需要使用现有的类，但是现有的类不符合系统需要，例如通过 音频播放器去播放 mp4格式文件。
 *
 * 具体操作：存在接口 A，B，C。 系统使用的是 A的实现 A-1，现在通过调用 A-1的方法去操作 B，C的实现所支持的操作。
 *         1⃣ 新增声明接口 A的实现A-2(这个就是适配器了)，在实现中声明 B，C的接口，通过判断参数，实例化具体的类型
 *         2⃣ 在 A的实现 A-1中判断，如果是自己不支持实现的类型，丢给适配器
 */
public class AdapterPattern {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }
}

// 媒体播放器
interface MediaPlayer {
    public void play(String audioType, String fileName);
}
// 高级媒体播放器
interface AdvancedMediaPlayer {
    public void playVlc(String fileName);
    public void playMp4(String fileName);
}
// 媒体播放器实现，支持mp3格式，不支持的丢给适配器
class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;

    public void play(String audioType, String fileName) {

        //播放 mp3 音乐文件的内置支持
        if(audioType.equalsIgnoreCase("mp3")){
            System.out.println("Playing mp3 file. Name: "+ fileName);
        }
        //mediaAdapter 提供了播放其他文件格式的支持
        else if(audioType.equalsIgnoreCase("vlc")
                || audioType.equalsIgnoreCase("mp4")){
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        }
        else{
            System.out.println("Invalid media. "+
                    audioType + " format not supported");
        }
    }
}
// 媒体播放器实现，适配器，通过参数判断调用高级媒体播放器【这里还可以加入更多的播放器类型去适配和调用】
class MediaAdapter implements MediaPlayer {

    AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType){
        if(audioType.equalsIgnoreCase("vlc") ){
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")){
            advancedMusicPlayer = new Mp4Player();
        }
    }

    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")){
            advancedMusicPlayer.playVlc(fileName);
        }else if(audioType.equalsIgnoreCase("mp4")){
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}
// 高级媒体播放器实现，支持 vlc格式文件
class VlcPlayer implements AdvancedMediaPlayer{
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: "+ fileName);
    }

    public void playMp4(String fileName) {
        //什么也不做
    }
}
// 高级媒体播放器实现，支持 mp4格式文件
class Mp4Player implements AdvancedMediaPlayer{

    public void playVlc(String fileName) {
        //什么也不做
    }

    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: "+ fileName);
    }
}
