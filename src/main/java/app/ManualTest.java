package app;

import com.google.gson.JsonObject;
import io.harness.cf.client.api.CfClient;
import io.harness.cf.client.api.Config;
import io.harness.cf.client.api.FeatureFlagInitializeException;
import io.harness.cf.client.dto.Target;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ManualTest {
    private static final String SDK_KEY = System.getenv("SDK_KEY");

    public static void main(String... args) throws InterruptedException {

        final CfClient client = new CfClient(SDK_KEY,
                Config.builder()
                        .configUrl("https://config.feature-flags.qa.harness.io/api/1.0")
                        .eventUrl("https://event.feature-flags.qa.harness.io/api/1.0")
                        .streamEnabled(false)
                        .analyticsEnabled(false)
                .build());
        try {
            client.waitForInitialization();
        } catch (FeatureFlagInitializeException e) {
            e.printStackTrace();
        }

        boolean boolResult;
        // bool 1
        boolResult = client.boolVariation("bool1", null, false);
        log.info("When bool1 is 'on' with on variation 'true' and off variation is 'false' then result should be 'true', got: '{}'", boolResult);
        assert boolResult : "expected true";
        // bool2
        boolResult = client.boolVariation("bool2", null, false);
        log.info("When bool2 is 'off' with on variation 'true' and off variation is 'false' then result should be 'false', got: '{}'", boolResult);
        assert !boolResult;
        // bool3
        boolResult = client.boolVariation("bool3", null, false);
        log.info("When bool3 is 'on' with on variation 'false' and off variation is 'true' then result should be 'false', got: '{}'", boolResult);
        assert !boolResult;
        // bool4
        boolResult = client.boolVariation("bool4", null, false);
        log.info("When bool4 is 'off' with on variation 'false' and off variation is 'true' then result should be 'true', got: '{}'", boolResult);
        assert boolResult;

        String strResult;
        // string1
        strResult = client.stringVariation("string1", null, "");
        log.info("When string1 is 'on' with on variation 'one' and off variation is 'two' then result should be 'one', got: '{}'", strResult);
        assert strResult.equals("one");
        // string2
        strResult = client.stringVariation("string2", null, "");
        log.info("When string2 is 'off' with on variation 'one' and off variation is 'two' then result should be 'two', got: '{}'", strResult);
        assert strResult.equals("two");
        // string3
        strResult = client.stringVariation("string3", null, "");
        log.info("When string3 is 'on' with on variation 'rosetti' and off variation is 'wilde' then result should be 'rosseti', got: '{}'", strResult);
        assert strResult.equals("“Oh where are you going with your love-locks flowing On the west wind blowing along this valley track?” “The downhill path is easy, come with me an it please ye, We shall escape the uphill by never turning back.” So they two went together in glowing August weather, The honey-breathing heather lay to their left and right; And dear she was to dote on, her swift feet seemed to float on The air like soft twin pigeons too sportive to alight. “Oh what is that in heaven where gray cloud-flakes are seven, Where blackest clouds hang riven just at the rainy skirt?” “Oh that’s a meteor sent us, a message dumb, portentous, An undeciphered solemn signal of help or hurt.” “Oh what is that glides quickly where velvet flowers grow thickly, Their scent comes rich and sickly?”—“A scaled and hooded worm.” “Oh what’s that in the hollow, so pale I quake to follow?” “Oh that’s a thin dead body which waits the eternal term.” “Turn again, O my sweetest,—turn again, false and fleetest: This beaten way thou beatest I fear is hell’s own track.” “Nay, too steep for hill-mounting; nay, too late for cost-counting: This downhill path is easy, but there’s no turning back.”");
        // string4
        strResult = client.stringVariation("string4", null, "");
        log.info("When string4 is 'off' with on variation 'rosetti' and off variation is 'wilde' then result should be 'wilde', got: '{}'", strResult);
        assert strResult.equals("Anti-mimesis is a philosophical position that holds the direct opposite of Aristotelian mimesis. Its most notable proponent is Oscar Wilde, who opined in his 1889 essay The Decay of Lying that, \"Life imitates Art far more than Art imitates Life\". In the essay, written as a Platonic dialogue, Wilde holds that anti-mimesis \"results not merely from Life's imitative instinct, but from the fact that the self-conscious aim of Life is to find expression, and that Art offers it certain beautiful forms through which it may realise that energy.\"[1][2] What is found in life and nature is not what is really there, but is that which artists have taught people to find there, through art. As in an example posited by Wilde, although there has been fog in London for centuries, one notices the beauty and wonder of the fog because \"poets and painters have taught the loveliness of such effects...They did not exist till Art had invented them.\"[1] McGrath places the antimimetic philosophy in a tradition of Irish writing, including Wilde and writers such as Synge and Joyce in a group that \"elevate blarney (in the form of linguistic idealism) to aesthetic and philosophical distinction\", noting that Terry Eagleton observes an even longer tradition that stretches \"as far back in Irish thought as the ninth-century theology of John Scottus Eriugena\" and \"the fantastic hyperbole of the ancient sagas\". Wilde's antimimetic idealism, specifically, McGrath describes to be part of the late nineteenth century debate between Romanticism and Realism.[1] Wilde's antimimetic philosophy has also had influence on later Irish writers, including Brian Friel. Halliwell asserts that the idea that life imitates art derives from classical notions that can be traced as far back as the writings of Aristophanes of Byzantium, and does not negate mimesis but rather \"displace[s] its purpose onto the artlike fashioning of life itself\". Halliwell draws a parallel between Wilde's philosophy and Aristophanes' famous question about the comedies written by Menander: \"O Menander and Life! Which of you took the other as your model?\", noting, however, that Aristophanes was a precursor to Wilde, and not necessarily espousing the positions that Wilde was later to propound.[3] In George Bernard Shaw's preface to Three Plays he wrote, \"I have noticed that when a certain type of feature appears in painting and is admired as beautiful, it presently becomes common in nature; so that the Beatrices and Francescas in the picture galleries of one generation come to life as the parlor-maids and waitresses of the next.\" He stated that he created the aristocratic characters in Cashel Byron's Profession as unrealistically priggish even without his later understanding that \"the real world does not exist...men and women are made by their own fancies in the image of the imaginary creatures in my youthful fictions, only much stupider.\" Shaw, however, disagreed with Wilde on some points. He considered most attempts by life to imitate art to be reprehensible, in part because the art that people generally chose to imitate was idealistic and romanticized.[4] Also well-known fiction writers explore broadly and magnificently the theme. Miguel de Cervantes Saavedra, author of the infamous Quixote, is one of the first modern writers who touches this topic while alluding to reality-fiction boundaries. Likewise, the Argentinian author Jorge Luis Borges explores the idea of reality imitating art mainly in his short stories “Tema del traidor y del héroe”, “Un problema”, “Un sueño” and “El evangelio según San Marcos”.");
        // string5
        strResult = client.stringVariation("string5", null, "");
        log.info("When string5 is 'on' with on variation '$£-_><~' and off variation is '^%&\"'\\/#' then result should be '$£-_><~', got: '{}'", strResult);
        assert strResult.equals("$£-_><~") : "expected $£-_><~";
        // string6
        strResult = client.stringVariation("string6", null, "");
        log.info("When string6 is 'off' with on variation '$£-_><~' and off variation is '^%&\"'\\/#' then result should be '^%&\"'\\/#', got: '{}'", strResult);
        assert strResult.equals("^%&\"'\\/#");

        double numberResult;
        // number1
        numberResult = client.numberVariation("number1", null, 0.0);
        log.info("When number1 is 'on' with on variation '1.5625478965213255454589785451236541256321' and off variation is '1.0' then result should be '1.5625478965213255454589785451236541256321', got: '{}'", numberResult);
        assert numberResult == 1.5625478965213255454589785451236541256321;
        // number2
        numberResult = client.numberVariation("number2", null, 0.0);
        log.info("When number2 is 'off' with on variation '1.5625478965213255454589785451236541256321' and off variation is '1.0' then result should be '1.0', got: '{}'", numberResult);
        assert numberResult == 1.0;

        JsonObject object;
        // json1
        object = client.jsonVariation("json1", null, new JsonObject());
        log.info("When json1 is 'on' with on variation '{''}' and off variation is '{'\"number\": \"5\",\"word\": \"five\",\"char\": \"&^%$\"}' then result should be '{''}', got: '{}'", object);
        assert object.size() == 0 : "result should be empty object";
        // json2
        object = client.jsonVariation("json2", null, new JsonObject());
        log.info("When json2 is 'off' with on variation '{''}' and off variation is '{'\"number\": \"5\",\"word\": \"five\",\"char\": \"&^%$\"}' then result should be '{'\"number\": \"5\",\"word\": \"five\",\"char\": \"&^%$\"}', got: '{}'", object);
        assert object.get("number").getAsString().equals("5") : "result should be empty object";

        // Flag Target Rules Boolean


        final Target bob =
                Target.builder()
                        .identifier("bob")
                        .isPrivate(false)
                        .name("Bob")
                        .build();

        // targetbool1
        boolResult = client.boolVariation("targetbool1", bob, false);
        log.info("When targetbool1 is 'on' with on variation 'false' and off variation is 'false' and target identifier bob then result should be 'true', got: '{}'", boolResult);
        assert boolResult : "expected true";

        // targetbool2
        boolResult = client.boolVariation("targetbool2", bob, true);
        log.info("When targetbool2 is 'off' with on variation 'true' and off variation is 'false' and target identifier bob then result should be 'false', got: '{}'", boolResult);
        assert !boolResult : "expected false";

        // targetbool3
        boolResult = client.boolVariation("targetbool3", bob, false);
        log.info("When targetbool3 is 'on' with on variation 'false' and off variation is 'true' and target identifier bob then result should be 'true', got: '{}'", boolResult);
        assert boolResult : "expected true";

        // targetbool4
        boolResult = client.boolVariation("targetbool4", bob, false);
        log.info("When targetbool4 is 'off' with on variation 'false' and off variation is 'true' and target identifier bob then result should be 'true', got: '{}'", boolResult);
        assert boolResult : "expected true";

        final Target jane =
                Target.builder()
                        .identifier("jane")
                        .isPrivate(false)
                        .name("Jane")
                        .build();

        // targetbool5
        boolResult = client.boolVariation("targetbool5", jane, true);
        log.info("When targetbool5 is 'on' with on variation 'true' and off variation is 'false' and target identifier jane then result should be 'false', got: '{}'", boolResult);
        assert !boolResult : "expected false";

        // targetbool6
        boolResult = client.boolVariation("targetbool6", jane, true);
        log.info("When targetbool6 is 'off' with on variation 'true' and off variation is 'false' and target identifier jane then result should be 'false', got: '{}'", boolResult);
        assert !boolResult : "expected false";

        // targetbool7
        boolResult = client.boolVariation("targetbool7", jane, true);
        log.info("When targetbool7 is 'on' with on variation 'false' and off variation is 'true' and target identifier jane then result should be 'false', got: '{}'", boolResult);
        assert !boolResult : "expected false";

        // targetbool8
        boolResult = client.boolVariation("targetbool8", jane, false);
        log.info("When targetbool8 is 'on' with on variation 'false' and off variation is 'true' and target identifier jane then result should be 'true', got: '{}'", boolResult);
        assert boolResult : "expected true";

        // stringtarget1
        strResult = client.stringVariation("stringtarget1", bob, "two");
        log.info("When stringtarget1 is 'on' with on variation 'one' and off variation is 'two' and target identifier bob then result should be 'one', got: '{}'", strResult);
        assert strResult.equals("one") : "expected one";

        // stringtarget2
        strResult = client.stringVariation("stringtarget2", bob, "three");
        log.info("When stringtarget2 is 'off' with on variation 'one' and off variation is 'two' and target identifier bob then result should be 'two', got: '{}'", strResult);
        assert strResult.equals("two") : "expected two";

        // targetnumber1
        numberResult = client.numberVariation("targetnumber1", bob, 0.0);
        log.info("When targetnumber1 is 'on' with on variation '0.11' and off variation is '0.25' and target identifier bob then result should be '0.11', got: '{}'", numberResult);
        assert numberResult == 0.11 : "expected 0.11";

        // targetnumber2
        numberResult = client.numberVariation("targetnumber2", bob, 0.0);
        log.info("When targetnumber1 is 'off' with on variation '0.11' and off variation is '0.25' and target identifier bob then result should be '0.25', got: '{}'", numberResult);
        assert numberResult == 0.25 : "expected 0.25";

        final Target jon =
                Target.builder()
                        .identifier("jon")
                        .isPrivate(false)
                        .name("Jon")
                        .build();

        // targetjson1
        object = client.jsonVariation("targetjson1", jon, new JsonObject());
        log.info("When targetjson1 is 'on' with on variation '{\"number\": \"£5\"}' and off variation is '{\"word\": \"five\"}' and target identifier jon then result should be '{\"number\": \"£5\"}', got: '{}'", object);
        assert object.get("number").getAsString().equals("£5") : "result should be {\"number\": \"£5\"}";

        // targetjson2
        object = client.jsonVariation("targetjson2", jon, new JsonObject());
        log.info("When targetjson2 is 'off' with on variation '{\"number\": \"£5\"}' and off variation is '{\"word\": \"five\"}' and target identifier jon then result should be '{\"word\": \"five\"}', got: '{}'", object);
        assert object.get("word").getAsString().equals("five") : "result should be {\"word\": \"five\"}";

        // AlwaysTrueWhenOn
        boolResult = client.boolVariation("AlwaysTrueWhenOn", bob, true);
        log.info("When AlwaysTrueWhenOn is 'on' with on variation 'true' and off variation is 'false' and target identifier bob then result should be 'false', got: '{}'", boolResult);
        assert !boolResult : "expected false";

        // AlwaysTrueWhenOn
        boolResult = client.boolVariation("AlwaysTrueWhenOn", jane, false);
        log.info("When AlwaysTrueWhenOn is 'on' with on variation 'true' and off variation is 'false' and target identifier jane then result should be 'true', got: '{}'", boolResult);
        assert boolResult : "expected true";

        // AlwaysFalseWhenOn
        boolResult = client.boolVariation("AlwaysFalseWhenOn", bob, false);
        log.info("When AlwaysFalseWhenOn is 'on' with on variation 'false' and off variation is 'true' and target identifier bob then result should be 'true', got: '{}'", boolResult);
        assert boolResult : "expected true";

        // AlwaysFalseWhenOn
        boolResult = client.boolVariation("AlwaysFalseWhenOn", jane, false);
        log.info("When AlwaysFalseWhenOn is 'on' with on variation 'false' and off variation is 'true' and target identifier jane then result should be 'false', got: '{}'", boolResult);
        assert !boolResult : "expected false";

        // release the resources
        client.close();
    }
}
