package com.philm2k.wordmaster.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.philm2k.wordmaster.model.dao.UsageDao;
import com.philm2k.wordmaster.model.dao.WordDao;
import com.philm2k.wordmaster.model.entity.Usage;
import com.philm2k.wordmaster.model.entity.Word;

@Database(entities = {Word.class,Usage.class}, version = 1, exportSchema = false)
public abstract class WordMasterDatabase extends RoomDatabase {
    private static WordMasterDatabase instance;

    public abstract WordDao wordDao();
    public abstract UsageDao usageDao();

    public static synchronized WordMasterDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder (context.getApplicationContext (),
                    WordMasterDatabase.class, "wordmaster-db" )
                    .fallbackToDestructiveMigration ()
                    .addCallback ( roomCallback )
                    .build ();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback (){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate ( db );
            new PopulateAsyncTask ( instance ).execute (  );
        }
    };

    private static class PopulateAsyncTask extends AsyncTask<Void, Void, Void> {

        private WordDao wordDao;
        private UsageDao usageDao;

        public PopulateAsyncTask(WordMasterDatabase db) {
            this.wordDao = db.wordDao ();
            this.usageDao = db.usageDao ();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            AddWords();
            AddUsages();
            return null;
        }

        private void AddUsages() {
            for(int i=1; i < 300; i ++){
                usageDao.insert(new Usage("","",i));
            }
        }

        void AddWords(){
            //Word Insert
            wordDao.insert ( new Word ("abbreviate","축약하다, 단축하다"));
            wordDao.insert ( new Word ("accommodate","수용하다"));
            wordDao.insert ( new Word ("accuse A of B","B를 이유로 A를 고발하다"));
            wordDao.insert ( new Word ("acquaint","익히다, 숙지하다"));
            wordDao.insert ( new Word ("adamantly","확고하게"));
            wordDao.insert ( new Word ("adequate","적절한"));
            wordDao.insert ( new Word ("adhere","고수하다"));
            wordDao.insert ( new Word ("adhesive","점착성의"));
            wordDao.insert ( new Word ("alleviate","줄이다, 완화하다"));
            wordDao.insert ( new Word ("amendment","개정"));
            wordDao.insert ( new Word ("anticipate","예상하다"));
            wordDao.insert ( new Word ("approve","승인하다, 찬성하다"));
            wordDao.insert ( new Word ("aspect","면, 측면"));
            wordDao.insert ( new Word ("aspire","열망하다"));
            wordDao.insert ( new Word ("assess","평가하다, 감정하다"));
            wordDao.insert ( new Word ("assume","맡다, 취하다"));
            wordDao.insert ( new Word ("assure","보증하다, 안심시키다"));
            wordDao.insert ( new Word ("apparently","외관상으로는, 분명히"));
            wordDao.insert ( new Word ("as to","~에 관하여"));
            wordDao.insert ( new Word ("assign","임무를 할당하다"));
            wordDao.insert ( new Word ("associate","관련시키다"));
            wordDao.insert ( new Word ("attendant","안내원, 참석자"));
            wordDao.insert ( new Word ("attentive","주의 깊은, 경청하는"));
            wordDao.insert ( new Word ("attraction","관광명소"));
            wordDao.insert ( new Word ("attribute","~에 귀착시키다"));
            wordDao.insert ( new Word ("banquet","연회"));
            wordDao.insert ( new Word ("barring","~이 없다면, ~을 제외하고"));
            wordDao.insert ( new Word ("be eligible for","~에 대한 자격이 있다"));
            wordDao.insert ( new Word ("be reluctant to","~하기를 꺼리다"));
            wordDao.insert ( new Word ("become acquaint with","~와 알게되다"));
            wordDao.insert ( new Word ("belated","때늦은"));
            wordDao.insert ( new Word ("bewilder","당황하게 하다"));
            wordDao.insert ( new Word ("boast","자랑하다"));
            wordDao.insert ( new Word ("by comparison","비교해 보면"));
            wordDao.insert ( new Word ("cautious","조심성 있는, 신중한"));
            wordDao.insert ( new Word ("certificate","자격정, 자격증을 교부하다"));
            wordDao.insert ( new Word ("clarify","분명히 하다"));
            wordDao.insert ( new Word ("coincide","일치하다"));
            wordDao.insert ( new Word ("colleague","동료"));
            wordDao.insert ( new Word ("commit","전념하다, 헌신하다"));
            wordDao.insert ( new Word ("committable","위탁할 수 있는"));
            wordDao.insert ( new Word ("companion","동료, 친구"));
            wordDao.insert ( new Word ("compartment","(물건 보관용)칸"));
            wordDao.insert ( new Word ("compatible","호환 가능한, 양립될 수 있는"));
            wordDao.insert ( new Word ("compliance","준수"));
            wordDao.insert ( new Word ("complimentary","무료의, 칭찬하는"));
            wordDao.insert ( new Word ("comply with","규정을 준수하다"));
            wordDao.insert ( new Word ("comprehensive","포괄적인"));
            wordDao.insert ( new Word ("conceal","숨기다"));
            wordDao.insert ( new Word ("concede","인정하다"));
            wordDao.insert ( new Word ("conceivably","아마도, 상상컨데"));
            wordDao.insert ( new Word ("concerning","~에 관하여"));
            wordDao.insert ( new Word ("conduct","수행하다, 처리하다"));
            wordDao.insert ( new Word ("confide","(비밀을)털어놓다"));
            wordDao.insert ( new Word ("confidentiality","기밀성"));
            wordDao.insert ( new Word ("congest","혼잡하게 하다"));
            wordDao.insert ( new Word ("conscious","의식이 있는"));
            wordDao.insert ( new Word ("consecutive","연속의, 연이은"));
            wordDao.insert ( new Word ("consent","동의, 허가"));
            wordDao.insert ( new Word ("consequent","~의 결과로 일어나는"));
            wordDao.insert ( new Word ("consistently","지속적으로"));
            wordDao.insert ( new Word ("conspicuous","눈에 띄는"));
            wordDao.insert ( new Word ("constraint","제약, 제한"));
            wordDao.insert ( new Word ("containment","견제, 억제"));
            wordDao.insert ( new Word ("contemporary","같은 시대의"));
            wordDao.insert ( new Word ("contingency","만일의 사태"));
            wordDao.insert ( new Word ("contraction","수축, 축소"));
            wordDao.insert ( new Word ("contrary","반대되는"));
            wordDao.insert ( new Word ("conveniently","편리하게"));
            wordDao.insert ( new Word ("convince","납득시키다"));
            wordDao.insert ( new Word ("cope with","~에 맞서다"));
            wordDao.insert ( new Word ("cordially","진심으로"));
            wordDao.insert ( new Word ("craft","공예품"));
            wordDao.insert ( new Word ("curb","억제하다"));
            wordDao.insert ( new Word ("cutting-edge","첨단의"));
            wordDao.insert ( new Word ("dedication","헌신"));
            wordDao.insert ( new Word ("defective","결함이 있는"));
            wordDao.insert ( new Word ("delicate","섬세한, 민감한"));
            wordDao.insert ( new Word ("designated","지정된"));
            wordDao.insert ( new Word ("detach","떼어내다"));
            wordDao.insert ( new Word ("detain","억류하다"));
            wordDao.insert ( new Word ("determined","단호한, 단단히 결심한"));
            wordDao.insert ( new Word ("devise","궁리하다, 고안하다"));
            wordDao.insert ( new Word ("devote","~에 바치다"));
            wordDao.insert ( new Word ("discard","버리다"));
            wordDao.insert ( new Word ("discerning","식별력 있는, 안목 있는"));
            wordDao.insert ( new Word ("disclose","밝히다, 드러내다"));
            wordDao.insert ( new Word ("discourteous","무례한"));
            wordDao.insert ( new Word ("discrepancy","차이, 불일치"));
            wordDao.insert ( new Word ("dispensable","없어도 되는"));
            wordDao.insert ( new Word ("disperse","해산시키다, 분산시키다"));
            wordDao.insert ( new Word ("distinctively","구별하여, 특징적으로"));
            wordDao.insert ( new Word ("distract","집중이 안 되게 하다"));
            wordDao.insert ( new Word ("diverge","분기하다, 나눠지다"));
            wordDao.insert ( new Word ("dormitory","기숙사"));
            wordDao.insert ( new Word ("drastic","강력한, 과감함"));
            wordDao.insert ( new Word ("dwelling","거주, 거처"));
            wordDao.insert ( new Word ("ecology","생태학"));
            wordDao.insert ( new Word ("eloquent","설득력 있는, 웅변력 있는"));
            wordDao.insert ( new Word ("emerge","드러나다, 나타나다"));
            wordDao.insert ( new Word ("emission","배출"));
            wordDao.insert ( new Word ("emphasize","강조하다"));
            wordDao.insert ( new Word ("endorsement","배서, 보증"));
            wordDao.insert ( new Word ("endowment","기증, 기부"));
            wordDao.insert ( new Word ("enhance","강화하다, 높이다"));
            wordDao.insert ( new Word ("entrepreneurship","기업가 정신"));
            wordDao.insert ( new Word ("erect","세우다"));
            wordDao.insert ( new Word ("essentially","본질적으로"));
            wordDao.insert ( new Word ("estranged from","~로부터 소원해진"));
            wordDao.insert ( new Word ("ethic","윤리"));
            wordDao.insert ( new Word ("evaluate","평가하다, 감정하다"));
            wordDao.insert ( new Word ("exaggerated","과장된"));
            wordDao.insert ( new Word ("exclusively","배타적으로"));
            wordDao.insert ( new Word ("exotic","이국적인"));
            wordDao.insert ( new Word ("execute","실행하다, 집행하다"));
            wordDao.insert ( new Word ("expel","내쫒다"));
            wordDao.insert ( new Word ("expertise","전문적 지식, 기술"));
            wordDao.insert ( new Word ("exquisite","정교한, 더없이 훌륭한"));
            wordDao.insert ( new Word ("extension number","내선 번호"));
            wordDao.insert ( new Word ("extensively","광범위하게"));
            wordDao.insert ( new Word ("extent","규모, 범위"));
            wordDao.insert ( new Word ("extinctions","멸종"));
            wordDao.insert ( new Word ("extracurricular","과외의, 정규 과목 이외의"));
            wordDao.insert ( new Word ("extraordinary","보기 드문, 탁월한"));
            wordDao.insert ( new Word ("extravagant","낭비벽이 있는"));
            wordDao.insert ( new Word ("extreme measure","극단적인 조치"));
            wordDao.insert ( new Word ("fabrication","제작"));
            wordDao.insert ( new Word ("facilitate","가능하게 하다"));
            wordDao.insert ( new Word ("fatal","치명적인"));
            wordDao.insert ( new Word ("feasibility","가능성, 실행 가능함"));
            wordDao.insert ( new Word ("feature","특징, 특징으로 하다"));
            wordDao.insert ( new Word ("fiasco","큰 실수, 대실패"));
            wordDao.insert ( new Word ("fine","벌금을 부과하다"));
            wordDao.insert ( new Word ("fixture","고정 세간, 붙박이"));
            wordDao.insert ( new Word ("flourish","번창하다"));
            wordDao.insert ( new Word ("fluctuation","변동"));
            wordDao.insert ( new Word ("for the time being","당분간"));
            wordDao.insert ( new Word ("formerly","이전에, 예전에"));
            wordDao.insert ( new Word ("forthcoming","다가오는"));
            wordDao.insert ( new Word ("fraud","사기"));
            wordDao.insert ( new Word ("freight","화물"));
            wordDao.insert ( new Word ("fringe benefit","부가혜택"));
            wordDao.insert ( new Word ("frustrate","좌절시키다, 실망시키다"));
            wordDao.insert ( new Word ("fulfill","이행하다"));
            wordDao.insert ( new Word ("fundamental","기본적인"));
            wordDao.insert ( new Word ("genuine","진짜의"));
            wordDao.insert ( new Word ("grant","허가하다"));
            wordDao.insert ( new Word ("haphazardly","우연히, 무턱대고, 되는 대로"));
            wordDao.insert ( new Word ("have familiarity with","~을 잘 알다, ~에 정통하다"));
            wordDao.insert ( new Word ("hazardous","위험한"));
            wordDao.insert ( new Word ("hepatitis","간염"));
            wordDao.insert ( new Word ("hierarchy","계층, 계급"));
            wordDao.insert ( new Word ("immensely","대단히, 엄청나게"));
            wordDao.insert ( new Word ("imperative","반드시 해야 하는"));
            wordDao.insert ( new Word ("implement","이행하다, 실행하다"));
            wordDao.insert ( new Word ("implication","밀접한 관계, 관련, 암시"));
            wordDao.insert ( new Word ("impose","부과하다, 강요하다"));
            wordDao.insert ( new Word ("in a timely manner","시의 적절하게"));
            wordDao.insert ( new Word ("in association with","~와 공동으로"));
            wordDao.insert ( new Word ("in compliance with","~에 따라, ~와 협력하여"));
            wordDao.insert ( new Word ("in light of","~에 비추어"));
            wordDao.insert ( new Word ("in terms of","~의 관점에서"));
            wordDao.insert ( new Word ("in the red","적자의"));
            wordDao.insert ( new Word ("incidence","발생정도, 범위"));
            wordDao.insert ( new Word ("inclination","경향, 기울어짐"));
            wordDao.insert ( new Word ("incur","초래하다, 발생시키다"));
            wordDao.insert ( new Word ("initiative","솔선, 독창성"));
            wordDao.insert ( new Word ("inquiry","질문, 문의"));
            wordDao.insert ( new Word ("insolent","무례한"));
            wordDao.insert ( new Word ("inspect","면밀하게 살피다, 점검하다"));
            wordDao.insert ( new Word ("instance","사례, 경우"));
            wordDao.insert ( new Word ("instigate","부추기다, 선동하다"));
            wordDao.insert ( new Word ("intangible","무형의"));
            wordDao.insert ( new Word ("intend to","~할 생각이다"));
            wordDao.insert ( new Word ("intensify","강화하다"));
            wordDao.insert ( new Word ("intention","의도"));
            wordDao.insert ( new Word ("interim","임시의"));
            wordDao.insert ( new Word ("intriguing","흥미를 자아내는"));
            wordDao.insert ( new Word ("iron things out","문제를 해결하다"));
            wordDao.insert ( new Word ("irrigation","관개"));
            wordDao.insert ( new Word ("isolation","고립, 고독"));
            wordDao.insert ( new Word ("itinerary","여행 일정표"));
            wordDao.insert ( new Word ("keep clear of","~에서 떨어져 있다"));
            wordDao.insert ( new Word ("keep track of","~에 대해 파악하고 있다"));
            wordDao.insert ( new Word ("lapse","착오, 실수"));
            wordDao.insert ( new Word ("lengthy","너무 긴, 장황한"));
            wordDao.insert ( new Word ("lessen","줄이다, 완화하다"));
            wordDao.insert ( new Word ("liable","~하기 쉬운, ~할 것 같은"));
            wordDao.insert ( new Word ("light refreshment","가벼운 다과"));
            wordDao.insert ( new Word ("liquidate","처분하다, 청산하다"));
            wordDao.insert ( new Word ("mandate","명령하다, 요구하다"));
            wordDao.insert ( new Word ("manipulation","조작"));
            wordDao.insert ( new Word ("markedly","두드러지게"));
            wordDao.insert ( new Word ("meager","부족한, 결핍된"));
            wordDao.insert ( new Word ("means","수단, 방법"));
            wordDao.insert ( new Word ("merely","단지"));
            wordDao.insert ( new Word ("merchandise","상품"));
            wordDao.insert ( new Word ("meteorological","기상의"));
            wordDao.insert ( new Word ("meticulously","꼼꼼하게"));
            wordDao.insert ( new Word ("minutes","회의록, 의사록"));
            wordDao.insert ( new Word ("morale","사기"));
            wordDao.insert ( new Word ("municipality","지방자치단체"));
            wordDao.insert ( new Word ("negligence","부주의, 태만"));
            wordDao.insert ( new Word ("novice","초보자"));
            wordDao.insert ( new Word ("numerically","수적으로"));
            wordDao.insert ( new Word ("objectify","객관화하다"));
            wordDao.insert ( new Word ("obligate","의무를 지우다"));
            wordDao.insert ( new Word ("oblige","부득이 ~하게 하다"));
            wordDao.insert ( new Word ("obscure","덮어 감추다"));
            wordDao.insert ( new Word ("obsolete","쓸모없게 된"));
            wordDao.insert ( new Word ("obstruct","방해하다"));
            wordDao.insert ( new Word ("occupancy","점유, 선점"));
            wordDao.insert ( new Word ("omission","생략"));
            wordDao.insert ( new Word ("opportune","시기가 좋은, 알맞은"));
            wordDao.insert ( new Word ("orchestrate","주관하다, 조정하다"));
            wordDao.insert ( new Word ("originate","비롯되다, 유래하다"));
            wordDao.insert ( new Word ("out of thin air","난데없이"));
            wordDao.insert ( new Word ("outpace","보다 속도가 빠르다"));
            wordDao.insert ( new Word ("outskirt","교외, 변두리"));
            wordDao.insert ( new Word ("overhead expense","경상비"));
            wordDao.insert ( new Word ("owing to","~때문에"));
            wordDao.insert ( new Word ("patronize","단골로 삼다"));
            wordDao.insert ( new Word ("perishable","썩기 쉬운"));
            wordDao.insert ( new Word ("permissible","허용되는, 허용할 수 있는"));
            wordDao.insert ( new Word ("perpetrate","범하다, 저지르다"));
            wordDao.insert ( new Word ("persistence","끈덕짐, 고집"));
            wordDao.insert ( new Word ("perspective","견지, 시각"));
            wordDao.insert ( new Word ("persuasive","설득력 있는"));
            wordDao.insert ( new Word ("pertain to","~와 관련되다"));
            wordDao.insert ( new Word ("phase out","단계적으로 없애다"));
            wordDao.insert ( new Word ("poised","(~을 할)준비가 다 된"));
            wordDao.insert ( new Word ("precaution","예방책"));
            wordDao.insert ( new Word ("precipitation","강수량"));
            wordDao.insert ( new Word ("precisely","정확히, 꼼꼼하게"));
            wordDao.insert ( new Word ("preferment","승진, 발탁"));
            wordDao.insert ( new Word ("preliminary","예비의, 사전 준비"));
            wordDao.insert ( new Word ("premises","구내, 건물(내)"));
            wordDao.insert ( new Word ("premier","최고의"));
            wordDao.insert ( new Word ("prevalent","유행하는, 널리 퍼진"));
            wordDao.insert ( new Word ("priority","우선순위"));
            wordDao.insert ( new Word ("probationary period","수습기간"));
            wordDao.insert ( new Word ("proficient","능숙한"));
            wordDao.insert ( new Word ("prohibit","금지하다"));
            wordDao.insert ( new Word ("prolong","늘리다, 연장하다"));
            wordDao.insert ( new Word ("prominent","두드러진"));
            wordDao.insert ( new Word ("prompt","신속한"));
            wordDao.insert ( new Word ("prone","~하기 쉬운, ~경향이 있는"));
            wordDao.insert ( new Word ("proportion","비율"));
            wordDao.insert ( new Word ("prosperous","번영하는, 번창한"));
            wordDao.insert ( new Word ("provision","조항, 공급"));
            wordDao.insert ( new Word ("proximity","근접"));
            wordDao.insert ( new Word ("puddle","웅덩이"));
            wordDao.insert ( new Word ("punctual","시간을 지키는"));
            wordDao.insert ( new Word ("pursue","추구하다, 밀고 나가다"));
            wordDao.insert ( new Word ("quote","인용하다"));
            wordDao.insert ( new Word ("raw materials","원자재"));
            wordDao.insert ( new Word ("reconcile","화해시키다"));
            wordDao.insert ( new Word ("rectify","고치다, 바로잡다"));
            wordDao.insert ( new Word ("reference","추천서"));
            wordDao.insert ( new Word ("refrain from ~ing","~을 그만두다, 삼가다"));
            wordDao.insert ( new Word ("regulate","규제하다, 조정하다"));
            wordDao.insert ( new Word ("reimburse","변상하다, 환급하다"));
            wordDao.insert ( new Word ("rein","억제하다"));
            wordDao.insert ( new Word ("relevant","관련된"));
            wordDao.insert ( new Word ("relieve","경감하다"));
            wordDao.insert ( new Word ("reluctant","꺼리는, 주저하는"));
            wordDao.insert ( new Word ("remedy","바로 잡다"));
            wordDao.insert ( new Word ("remit","송금하다, 면제하다"));
            wordDao.insert ( new Word ("reoccurrence","재발"));
            wordDao.insert ( new Word ("respective","각각의"));
            wordDao.insert ( new Word ("respirator","인공호흡장치"));
            wordDao.insert ( new Word ("retail","소매의, 소매하다"));
            wordDao.insert ( new Word ("retain","보관하다"));
            wordDao.insert ( new Word ("retrieve","회수하다"));
            wordDao.insert ( new Word ("revise","정정하다, 수정하다"));
            wordDao.insert ( new Word ("ridicule","비웃다"));
            wordDao.insert ( new Word ("rigorous","엄격한, 혹독한"));
            wordDao.insert ( new Word ("rural","시골의"));
            wordDao.insert ( new Word ("sanctions against","~에 대한 제재"));
            wordDao.insert ( new Word ("secure a patent","특허를 획득하다"));
            wordDao.insert ( new Word ("shred","잘게 자르다, 찢다"));
            wordDao.insert ( new Word ("shrub","관목"));
            wordDao.insert ( new Word ("significant","상당한, 의미있는, 중대한"));
            wordDao.insert ( new Word ("sparsely","희박하게, 드문드문"));
            wordDao.insert ( new Word ("specify","명시하다"));
            wordDao.insert ( new Word ("standing ovation","기립박수"));
            wordDao.insert ( new Word ("sternly","엄격하게"));
            wordDao.insert ( new Word ("stimulate","자극하다, 촉진하다"));
            wordDao.insert ( new Word ("stipulate","규정하다"));
            wordDao.insert ( new Word ("stock farmer","목축업자"));
            wordDao.insert ( new Word ("stingently","엄중하게"));
            wordDao.insert ( new Word ("strive to","~하기 위해 노력하다"));
            wordDao.insert ( new Word ("subject to","~을 겪게 하다"));
            wordDao.insert ( new Word ("submit","제출하다"));
            wordDao.insert ( new Word ("subsequent","다음의, 그후의"));
            wordDao.insert ( new Word ("supplement","보충하다, 메우다"));
            wordDao.insert ( new Word ("suspicious","의심하는"));
            wordDao.insert ( new Word ("sustain","지탱하다, 유지하다"));
            wordDao.insert ( new Word ("symptom","증상"));
            wordDao.insert ( new Word ("tailor A to B","A를 B에 맞추다"));
            wordDao.insert ( new Word ("take into account","~을 고려하다"));
            wordDao.insert ( new Word ("take it back","취소하다"));
            wordDao.insert ( new Word ("tedious","지루한"));
            wordDao.insert ( new Word ("tentatively","임시로"));
            wordDao.insert ( new Word ("testify","증언하다"));
            wordDao.insert ( new Word ("to some extent","어느 정도"));
            wordDao.insert ( new Word ("trivia","사소한 일"));
            wordDao.insert ( new Word ("tuition","수업료"));
            wordDao.insert ( new Word ("turmoil","혼란, 소란"));
            wordDao.insert ( new Word ("twofold","두 부분의"));
            wordDao.insert ( new Word ("undergo","겪다"));
            wordDao.insert ( new Word ("upkeep","양육, 유지"));
            wordDao.insert ( new Word ("utmost","최대의"));
            wordDao.insert ( new Word ("utilize","활용하다"));
            wordDao.insert ( new Word ("vaguely","모호하게, 막연히"));
            wordDao.insert ( new Word ("valid","유요한, 합법적인"));
            wordDao.insert ( new Word ("variable","변하기 쉬운"));
            wordDao.insert ( new Word ("vastly","방대하게, 대단히"));
            wordDao.insert ( new Word ("verbalize","말로 나타내다"));
            wordDao.insert ( new Word ("verify","확인하다"));
            wordDao.insert ( new Word ("versatile","다용도의"));
            wordDao.insert ( new Word ("vital","중요한, 필수적인"));
            wordDao.insert ( new Word ("waive","면제하다, 포기하다"));
            wordDao.insert ( new Word ("weightlessness","무중력"));
            wordDao.insert ( new Word ("willingly","기꺼이, 자진해서"));
            wordDao.insert ( new Word ("workmanship","기량, 솜씨"));
            wordDao.insert ( new Word ("worrisome","걱정스러운"));
        }
    }
}
