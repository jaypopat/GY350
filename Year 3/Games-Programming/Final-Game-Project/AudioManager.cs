using UnityEngine;
using System.Collections.Generic;

public class AudioManager : MonoBehaviour
{
    public static AudioManager Instance;

    [Header("Audio Sources")]
    public AudioSource musicSource;
    public AudioSource sfxSource;

    [Header("Audio Clips")]
    [SerializeField] private List<AudioClip> audioClips;
    private Dictionary<string, AudioClip> audioLibrary;

    private void Awake()
    {
        if (Instance == null)
        {
            Instance = this;
        }

        audioLibrary = new Dictionary<string, AudioClip>();
        foreach (var clip in audioClips)
        {
            audioLibrary[clip.name] = clip;
        }
        PlayMusic("main");
    }

    private void PlayMusic(string clipName)
    {
        if (audioLibrary.TryGetValue(clipName, out var clip))
        {
            musicSource.clip = clip;
            musicSource.loop = true;
            musicSource.Play();
        }
        else
        {
            Debug.Log("music not found");
        }
    }
    public void PlaySFX(string clipName)
    {
        if (audioLibrary.TryGetValue(clipName, out var clip))
        {
            sfxSource.PlayOneShot(clip);
        }
        else
        {
            Debug.Log("no sound for that name");
        }
    }
    public void StopMusic()
    {
        musicSource.Stop();
    }
}
