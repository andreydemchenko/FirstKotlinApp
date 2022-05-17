package ru.turbopro.jokes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "AndroidExample";
    private RecyclerView recyclerView;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Read content of company.json
        String jsonText = null;
        JSONObject jsonRoot = null;
        try {
            jsonText = readText(this, R.raw.jokes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            jsonRoot = new JSONObject(jsonText);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonRoot.getJSONArray("Jokes");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String[] jokes = new String[jsonArray.length()];;
        for(int i = 0; i < jsonArray.length(); i++) {
            try {
                jokes[i] = jsonArray.getString(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        this.recyclerView = (RecyclerView) this.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new RVAdapter(this, Arrays.asList(jokes)));

        // RecyclerView scroll vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private static String readText(Context context, int resId) throws IOException {
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String s = null;
        while((s = br.readLine()) != null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.itemSearch);
        mSearchView = (SearchView) searchItem.getActionView();

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //deleteItem.setVisible(true);
        //chatAdapter.finalUpdateList();

        switch (item.getItemId()) {
            case android.R.id.home: {
                this.finish();
                return true;
            }
            case R.id.itemSetting: {

            }
            case R.id.itemSearch:{
                mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        mSearchView.clearFocus();
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        //chatAdapter.filter(newText);
                        return false;
                    }
                });
                mSearchView.setQueryHint("поиск");
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<Joke> getListData() {
        List<Joke> list = new ArrayList<Joke>();
        Joke one = new Joke("Смотрю на свою сестру, как она спит.\n" +
                "Так сладко улыбается во сне.\n" +
                "Наверное убила кого-то и ест.");
        Joke two = new Joke("Не могу стоять когда другие работают, пойду полежу.");
        Joke three = new Joke("Мои ошибки многому меня научили. Подумываю о том, чтобы совершить еще несколько.");
        Joke four = new Joke("Ровное поле, круглый мяч, хорошая погода — все было против сборной\n" +
                "России по футболу!");
        Joke five = new Joke("Вот иногда приходишь на работу и понимаешь, что люди вокруг какие-то ИДИНАХОВЫЕ.");
        Joke six = new Joke("Люся Сидорова наконец дошла до того места, куда её послали.\n" +
                "Говорит, что пока ей здесь очень нравится...");
        Joke seven = new Joke("Самый прекрасный изгиб в теле женщины - ее улыбка.");
        Joke eight = new Joke("Отсутствие волос на голове мужчины - это дополнительная площадь для поцелуев!");
        Joke nine = new Joke("И вдруг на пляже я заметил, как хороша она ни в чём.");
        Joke ten = new Joke("С тех пор, как от меня ушла Люда, я стал нелюдимым...");

        list.add(one);
        list.add(two);
        list.add(three);
        list.add(four);
        list.add(five);
        list.add(six);
        list.add(seven);
        list.add(eight);
        list.add(nine);
        list.add(ten);

        return list;
    }

/*    private void parseWithXmlPullParser() {
        LayoutInflater inflater = getLayoutInflater();
        CardView cardView = null;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(50, 50, 50, 50);

        try {
            XmlPullParser xmlPullParser = getResources().getXml(R.xml.wifi_config_store);
            while (xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT) {

                switch (xmlPullParser.getEventType()) {
                    case XmlPullParser.START_DOCUMENT: {
                        if (BuildConfig.DEBUG) {
                            Log.d("LOG_TAG", "START_DOCUMENT");
                        }
                        break;
                    }
                    // начало тега
                    case XmlPullParser.START_TAG: {
                        if (BuildConfig.DEBUG) {
                            Log.d("LOG_TAG", "START_TAG: имя тега = "
                                    + xmlPullParser.getName()
                                    + ", уровень = "
                                    + xmlPullParser.getDepth()
                                    + ", число атрибутов = "
                                    + xmlPullParser.getAttributeCount());
                        }

                        if (xmlPullParser.getName().equals("WifiConfiguration")) {
                            cardView = (CardView) inflater.inflate(R.layout.wifi_info, null);
                            cardView.setLayoutParams(params);
                        }

                        if (xmlPullParser.getName().equals("string")) {
                            if (xmlPullParser.getAttributeValue(null, "name").equals("SSID")) {
                                TextView textViewSSID = cardView.findViewById(R.id.textSsid);
                                textViewSSID.setText(textViewSSID.getText() + " " + xmlPullParser.nextText());
                                break;
                            }

                            if (xmlPullParser.getAttributeValue(null, "name").equals("PreSharedKey")) {
                                TextView textViewPreShareKey = cardView.findViewById(R.id.textPreShareKey);
                                textViewPreShareKey.setText(
                                        textViewPreShareKey.getText() + " " + xmlPullParser.nextText());
                                break;
                            }
                        }

                        if (xmlPullParser.getName().equals("boolean")) {
                            if (xmlPullParser.getAttributeValue(null, "name").equals("HiddenSSID")) {
                                TextView textViewHiddenSSID = cardView.findViewById(R.id.textHiddenSsid);
                                textViewHiddenSSID.setText(
                                        textViewHiddenSSID.getText() + " " + xmlPullParser.getAttributeValue(null,
                                                "value"));
                                break;
                            }
                        }
                        break;
                    }
                    // конец тега
                    case XmlPullParser.END_TAG:
                        if (BuildConfig.DEBUG) {
                            Log.d("LOG_TAG", "END_TAG: имя тега = " + xmlPullParser.getName());
                        }

                        if (xmlPullParser.getName().equals("WifiConfiguration")) {
                            linearLayout.addView(cardView);
                        }

                        break;
                    // содержимое тега
                    case XmlPullParser.TEXT:
                        if (BuildConfig.DEBUG) {
                            Log.d("LOG_TAG", "текст = " + xmlPullParser.getText());
                        }
                        break;

                    default:
                        break;
                }
                xmlPullParser.next();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }*/
}