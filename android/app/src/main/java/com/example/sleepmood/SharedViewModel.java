package com.example.sleepmood;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class SharedViewModel extends ViewModel {
    // LiveData와 List 따로 관리하기 LiveData만 가지고 데이터 수정하면 너무 손이 많이간다
    // 차라리 List 가지고 따로 관리하는게 좋을듯
    private final MutableLiveData<UserData> liveUserData = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<AlarmData>> liveAlarmData = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<DiaryData>> liveDiaryData = new MutableLiveData<>();

    ArrayList<AlarmData> alarmDataitems = new ArrayList<>();
    ArrayList<DiaryData> diaryDataitems = new ArrayList<>();

    public LiveData<UserData> getLiveUserData() {
        return liveUserData;
    }

    public void setLiveUserData(UserData userData) {
        liveUserData.setValue(userData);
    }

    public LiveData<ArrayList<AlarmData>> getLiveAlarmData() {
        return liveAlarmData;
    }

    //초기설정
    public void setAllLiveAlarmData(ArrayList<AlarmData> alarmData) {
        alarmDataitems = alarmData;
        liveAlarmData.setValue(alarmDataitems);
    }

    // 수정
    public void setLiveAlarmData(AlarmData beforeAlarmData, AlarmData afterAlarmData) {
        for (int i = 0; i < alarmDataitems.size(); i++) {
            if (alarmDataitems.get(i).getAlarmDate() == beforeAlarmData.getAlarmDate() &&
                    alarmDataitems.get(i).getAlarmTime() == beforeAlarmData.getAlarmTime() &&
                    alarmDataitems.get(i).getAlarmRepeatWeek() == beforeAlarmData.getAlarmRepeatWeek()) {
                alarmDataitems.set(i, afterAlarmData);
                break;
            }

        }
        liveAlarmData.setValue(alarmDataitems);

    }

    // 더하기
    public void addLiveAlarmData(AlarmData alarmData) {
        alarmDataitems.add(alarmData);

        for (int i = 0; i < alarmDataitems.size(); i++) {
            System.out.println(alarmDataitems.get(i));
        }

        liveAlarmData.setValue(alarmDataitems);

    }

    // 삭제
    public void deleteLiveAlarmData(AlarmData alarmData) {
        for (int i = 0; i < alarmDataitems.size(); i++) {
            if (alarmDataitems.get(i).getAlarmDate() == alarmData.getAlarmDate() &&
                    alarmDataitems.get(i).getAlarmTime() == alarmData.getAlarmTime() &&
                    alarmDataitems.get(i).getAlarmRepeatWeek() == alarmData.getAlarmRepeatWeek()) {
                alarmDataitems.remove(i);
                break;
            }

        }
        liveAlarmData.setValue(alarmDataitems);
    }


    public LiveData<ArrayList<DiaryData>> getLiveDiaryData() {
        return liveDiaryData;
    }

    //초기설정
    public void setAllLiveDiaryData(ArrayList<DiaryData> diaryData) {
        diaryDataitems = diaryData;
        liveDiaryData.setValue(diaryDataitems);
    }

    // 수정
    public void setLiveDiaryData(DiaryData beforeDiaryData, DiaryData afterDiaryData) {
        for (int i = 0; i < diaryDataitems.size(); i++) {
            if (diaryDataitems.get(i).getEmail() == beforeDiaryData.getEmail()) {
                diaryDataitems.set(i, afterDiaryData);
                break;
            }

        }
        liveDiaryData.setValue(diaryDataitems);

    }

    // 더하기
    public void addLiveDiaryData(DiaryData diaryData) {
        diaryDataitems.add(diaryData);

        for (int i = 0; i < diaryDataitems.size(); i++) {
            System.out.println(diaryDataitems.get(i));
        }

        liveDiaryData.setValue(diaryDataitems);

    }

    // 삭제
    public void deleteLiveDiaryData(DiaryData diaryData) {
        for (int i = 0; i < diaryDataitems.size(); i++) {
            if (diaryDataitems.get(i).getEmail() == diaryData.getEmail()) {
                diaryDataitems.remove(i);
                break;
            }
            liveDiaryData.setValue(diaryDataitems);
        }
    }
}

