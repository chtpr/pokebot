package edu.northeastern.cs5500.starterbot.command;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

@Module
public class CommandModule {

    @Provides
    @IntoSet
    public SlashCommandHandler provideCatchCommand(CatchCommand catchCommand) {
        return catchCommand;
    }

    @Provides
    @IntoSet
    public SlashCommandHandler provideTradeCommand(TradeCommand tradeCommand) {
        return tradeCommand;
    }

    @Provides
    @IntoSet
    public SlashCommandHandler provideBattleCommand(BattleCommand battleCommand) {
        return battleCommand;
    }

    @Provides
    @IntoSet
    public ButtonClickHandler provideChooseStarterButtonClickHandler(
            ChooseStarterButtonClick buttonClick) {
        return buttonClick;
    }

    @Provides
    @IntoSet
    public ButtonClickHandler provideCatchButtonClickHandler(CatchButtonClick catchbuttonClick) {
        return catchbuttonClick;
    }

    @Provides
    @IntoSet
    public ButtonClickHandler provideTradeButtonClickHandleAccept(
            TradeButtonClickHandleAccept tradeButtonClick) {
        return tradeButtonClick;
    }

    @Provides
    @IntoSet
    public ButtonClickHandler provideTradeButtonClickHandleDecline(
            TradeButtonClickHandleDecline tradeButtonClick) {
        return tradeButtonClick;
    }

    @Provides
    @IntoSet
    public ButtonClickHandler provideTradeButtonClickHandleCancel(
            TradeButtonClickHandleCancel tradeButtonClick) {
        return tradeButtonClick;
    }

    @Provides
    @IntoSet
    public ButtonClickHandler provideTradeConfirmClickHandler(
            TradeButtonClickHandleConfirm tradeButtonClick) {
        return tradeButtonClick;
    }

    @Provides
    @IntoSet
    public SelectionMenuHandler provideTradeSelectionMenuHandler(
            TradeSelectionMenuHandler tradeSelectionMenuHandler) {
        return tradeSelectionMenuHandler;
    }

    @Provides
    @IntoSet
    public ButtonClickHandler provideBattleButtonClickHandleAccept(
            BattleButtonClickHandleAccept battleButtonClick) {
        return battleButtonClick;
    }

    @Provides
    @IntoSet
    public ButtonClickHandler provideBattleButtonClickHandleDecline(
            BattleButtonClickHandleDecline battleButtonClick) {
        return battleButtonClick;
    }

    @Provides
    @IntoSet
    public ButtonClickHandler provideBattleButtonClickHandleCancel(
            BattleButtonClickHandleCancel battleButtonClick) {
        return battleButtonClick;
    }

    @Provides
    @IntoSet
    public SelectionMenuHandler provideBattleSelectionMenuHandler(
            BattleSelectionMenuHandler battleSelectionMenuHandler) {
        return battleSelectionMenuHandler;
    }

    @Provides
    @IntoSet
    public ButtonClickHandler provideBattleConfirmClickHandler(
            BattleButtonClickHandleConfirm battleButtonClick) {
        return battleButtonClick;
    }

    @Provides
    @IntoSet
    public SlashCommandHandler provideViewPartyCommand(ViewPartyCommand viewPartyCommand) {
        return viewPartyCommand;
    }

    @Provides
    @IntoSet
    public SlashCommandHandler provideInspectPokemonCommand(
            InspectPokemonCommand inspectPokemonCommand) {
        return inspectPokemonCommand;
    }

    @Provides
    @IntoSet
    public SlashCommandHandler provideDeletePokemonCommand(
            DeletePokemonCommand deletePokemonCommand) {
        return deletePokemonCommand;
    }

    @Provides
    @IntoSet
    public BeginPlayingCommand provideBeginPlayingCommand(BeginPlayingCommand beginPlayingCommand) {
        return beginPlayingCommand;
    }
}
