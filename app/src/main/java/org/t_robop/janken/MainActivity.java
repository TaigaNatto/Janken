package org.t_robop.janken;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //0:g,1:t,2:p
    //プレイヤーの手の判定
    int player=0;
    //相手の手の判定
    int enemy;

    //現在の戦闘回数
    int BattleNum=1;
    //現在の勝敗数
    float BattleWin;
    float BattleLose;
    //計算用スロット
    float CalBox;

    //スキルスロット判定
    int SkillJudgeP;
    int SkillJudgeE;

    //0:draw,1:win,2:lose
    //勝敗判定
    int match;

    AlertDialog.Builder resultDL;

    TextView Num;
    TextView Per;
    TextView aoriG;


    public void gue(View v)
    {
        player=0;//グーが押されました

        Randomon();
        Battle();
        Result();
    }

    public void tiki(View v)
    {
        player=1;//チョキが押されました

        Randomon();
        Battle();
        Result();
    }

    public void par(View v)
    {
        player=2;//パーが押されました

        Randomon();
        Battle();
        Result();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Num=(TextView)findViewById(R.id.battlenum);
        Per=(TextView)findViewById(R.id.battleparsent);
        aoriG=(TextView)findViewById(R.id.aori);

        resultDL=new AlertDialog.Builder(this);
    }



//回答ダイアログの表示
    public void Result() {
        String judge[] = {"相打ち","勝利", "敗北"};
        String skill[][] = {
                {"強烈なストレート", "蜂の如き目潰し", "圧倒的平手"},
                {"軽いジャブ", "手刀", "全てを呑み込む両手"},
                {"ロケットパンチ", "ナイフ", "ゴールキーパー"},
                {"グー", "チョキ", "パー"}
        };

        //DLタイトル表示
        resultDL.setTitle(judge[match]);

        //DLメッセ表示
        resultDL.setMessage(
                "あなたは"+skill[SkillJudgeP][player]+"を繰り出した！"+"\n"+
                "相手は"+skill[SkillJudgeE][enemy]+"で応戦した！"+"\n"+
                "あなたは"+judge[match]+"した！お疲れ！");

        //DLボタン表示+クリック処理
        resultDL.setPositiveButton("次へ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                //試合回数の加算
                BattleNum=BattleNum+1;
                Num.setText(BattleNum+"試合目");

                //勝率の計算と表示
                CalBox=BattleWin/(BattleNum-1);
                Per.setText(CalBox*100+"%");
                CalBox=0;

                Aori();
            }
        });

        resultDL.create().show();
    }

    //相手の手を乱数決定
    public void Randomon()
    {
        enemy=rand(3);
        //スキルスロット確定
        SkillJudgeP=rand(4);
        SkillJudgeE=rand(4);
    }

    public int rand(int num){
        Random r = new Random();
        return  r.nextInt(num);
    }


    //じゃんけんシステム
    public  void Battle()
    {
        if(player==enemy)
        {
            match=0;
        }
        else if(player==0)
        {
            if(enemy==1)
            {
                match=1;
            }
            else
            {
                match=2;
            }
        }
        else if(player==1)
        {
            if(enemy==2)
            {
                match=1;
            }
            else
            {
                match=2;
            }
        }
        else if(player==2)
        {
            if(enemy==0)
            {
                match=1;
            }
            else
            {
                match=2;
            }
        }

        //勝敗加算
        if(match==1)
        {
            BattleWin=BattleWin+1;
        }
        else if(match==2)
        {
            BattleLose=BattleLose+1;
        }
    }

    public void Aori()
    {
        if(BattleLose==BattleWin)
        {
            aoriG.setText("切迫した状況だ！");
        }
        else if(BattleWin>BattleLose)
        {
            aoriG.setText("なんて強いんだ！");
        }
        else if(BattleWin<BattleLose)
        {
            aoriG.setText("本当に弱いのう");
        }
    }

}

