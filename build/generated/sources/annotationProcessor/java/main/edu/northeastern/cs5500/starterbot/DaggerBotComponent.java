package edu.northeastern.cs5500.starterbot;

import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import dagger.internal.SetBuilder;
import edu.northeastern.cs5500.starterbot.command.BattleButtonClickHandleAccept;
import edu.northeastern.cs5500.starterbot.command.BattleButtonClickHandleAccept_Factory;
import edu.northeastern.cs5500.starterbot.command.BattleButtonClickHandleCancel;
import edu.northeastern.cs5500.starterbot.command.BattleButtonClickHandleCancel_Factory;
import edu.northeastern.cs5500.starterbot.command.BattleButtonClickHandleConfirm;
import edu.northeastern.cs5500.starterbot.command.BattleButtonClickHandleConfirm_Factory;
import edu.northeastern.cs5500.starterbot.command.BattleButtonClickHandleDecline;
import edu.northeastern.cs5500.starterbot.command.BattleButtonClickHandleDecline_Factory;
import edu.northeastern.cs5500.starterbot.command.BattleCommand;
import edu.northeastern.cs5500.starterbot.command.BattleCommand_Factory;
import edu.northeastern.cs5500.starterbot.command.BattleSelectionMenuHandler;
import edu.northeastern.cs5500.starterbot.command.BattleSelectionMenuHandler_Factory;
import edu.northeastern.cs5500.starterbot.command.ButtonClickHandler;
import edu.northeastern.cs5500.starterbot.command.CatchButtonClick;
import edu.northeastern.cs5500.starterbot.command.CatchButtonClick_Factory;
import edu.northeastern.cs5500.starterbot.command.CatchCommand;
import edu.northeastern.cs5500.starterbot.command.CatchCommand_Factory;
import edu.northeastern.cs5500.starterbot.command.ChooseStarterButtonClick;
import edu.northeastern.cs5500.starterbot.command.ChooseStarterButtonClick_Factory;
import edu.northeastern.cs5500.starterbot.command.ChooseStarterCommand;
import edu.northeastern.cs5500.starterbot.command.ChooseStarterCommand_Factory;
import edu.northeastern.cs5500.starterbot.command.CommandModule;
import edu.northeastern.cs5500.starterbot.command.CommandModule_ProvideBattleButtonClickHandleAcceptFactory;
import edu.northeastern.cs5500.starterbot.command.CommandModule_ProvideBattleButtonClickHandleCancelFactory;
import edu.northeastern.cs5500.starterbot.command.CommandModule_ProvideBattleButtonClickHandleDeclineFactory;
import edu.northeastern.cs5500.starterbot.command.CommandModule_ProvideBattleCommandFactory;
import edu.northeastern.cs5500.starterbot.command.CommandModule_ProvideBattleConfirmClickHandlerFactory;
import edu.northeastern.cs5500.starterbot.command.CommandModule_ProvideBattleSelectionMenuHandlerFactory;
import edu.northeastern.cs5500.starterbot.command.CommandModule_ProvideCatchButtonClickHandlerFactory;
import edu.northeastern.cs5500.starterbot.command.CommandModule_ProvideCatchCommandFactory;
import edu.northeastern.cs5500.starterbot.command.CommandModule_ProvideChooseStarterButtonClickHandlerFactory;
import edu.northeastern.cs5500.starterbot.command.CommandModule_ProvideDeletePokemonCommandFactory;
import edu.northeastern.cs5500.starterbot.command.CommandModule_ProvideInspectPokemonCommandFactory;
import edu.northeastern.cs5500.starterbot.command.CommandModule_ProvideTradeButtonClickHandleAcceptFactory;
import edu.northeastern.cs5500.starterbot.command.CommandModule_ProvideTradeButtonClickHandleCancelFactory;
import edu.northeastern.cs5500.starterbot.command.CommandModule_ProvideTradeButtonClickHandleDeclineFactory;
import edu.northeastern.cs5500.starterbot.command.CommandModule_ProvideTradeCommandFactory;
import edu.northeastern.cs5500.starterbot.command.CommandModule_ProvideTradeConfirmClickHandlerFactory;
import edu.northeastern.cs5500.starterbot.command.CommandModule_ProvideTradeSelectionMenuHandlerFactory;
import edu.northeastern.cs5500.starterbot.command.CommandModule_ProvideViewPartyCommandFactory;
import edu.northeastern.cs5500.starterbot.command.DeletePokemonCommand;
import edu.northeastern.cs5500.starterbot.command.DeletePokemonCommand_Factory;
import edu.northeastern.cs5500.starterbot.command.GreetNewUserCommand;
import edu.northeastern.cs5500.starterbot.command.GreetNewUserCommand_Factory;
import edu.northeastern.cs5500.starterbot.command.GreetReturningUserCommand;
import edu.northeastern.cs5500.starterbot.command.GreetReturningUserCommand_Factory;
import edu.northeastern.cs5500.starterbot.command.InspectPokemonCommand;
import edu.northeastern.cs5500.starterbot.command.InspectPokemonCommand_Factory;
import edu.northeastern.cs5500.starterbot.command.SelectionMenuHandler;
import edu.northeastern.cs5500.starterbot.command.SlashCommandHandler;
import edu.northeastern.cs5500.starterbot.command.TradeButtonClickHandleAccept;
import edu.northeastern.cs5500.starterbot.command.TradeButtonClickHandleAccept_Factory;
import edu.northeastern.cs5500.starterbot.command.TradeButtonClickHandleCancel;
import edu.northeastern.cs5500.starterbot.command.TradeButtonClickHandleCancel_Factory;
import edu.northeastern.cs5500.starterbot.command.TradeButtonClickHandleConfirm;
import edu.northeastern.cs5500.starterbot.command.TradeButtonClickHandleConfirm_Factory;
import edu.northeastern.cs5500.starterbot.command.TradeButtonClickHandleDecline;
import edu.northeastern.cs5500.starterbot.command.TradeButtonClickHandleDecline_Factory;
import edu.northeastern.cs5500.starterbot.command.TradeCommand;
import edu.northeastern.cs5500.starterbot.command.TradeCommand_Factory;
import edu.northeastern.cs5500.starterbot.command.TradeSelectionMenuHandler;
import edu.northeastern.cs5500.starterbot.command.TradeSelectionMenuHandler_Factory;
import edu.northeastern.cs5500.starterbot.command.ViewPartyCommand;
import edu.northeastern.cs5500.starterbot.command.ViewPartyCommand_Factory;
import edu.northeastern.cs5500.starterbot.controller.BattleController;
import edu.northeastern.cs5500.starterbot.controller.BattleController_Factory;
import edu.northeastern.cs5500.starterbot.controller.PokedexController;
import edu.northeastern.cs5500.starterbot.controller.PokedexController_Factory;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController_Factory;
import edu.northeastern.cs5500.starterbot.controller.TradeController;
import edu.northeastern.cs5500.starterbot.controller.TradeController_Factory;
import edu.northeastern.cs5500.starterbot.controller.UserInventoryController;
import edu.northeastern.cs5500.starterbot.controller.UserInventoryController_Factory;
import edu.northeastern.cs5500.starterbot.listener.GuildListener;
import edu.northeastern.cs5500.starterbot.listener.GuildListener_Factory;
import edu.northeastern.cs5500.starterbot.listener.GuildListener_MembersInjector;
import edu.northeastern.cs5500.starterbot.listener.MessageListener;
import edu.northeastern.cs5500.starterbot.listener.MessageListener_Factory;
import edu.northeastern.cs5500.starterbot.listener.MessageListener_MembersInjector;
import edu.northeastern.cs5500.starterbot.model.Battle;
import edu.northeastern.cs5500.starterbot.model.PokedexEntry;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.model.Trade;
import edu.northeastern.cs5500.starterbot.model.UserInventory;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import edu.northeastern.cs5500.starterbot.repository.MongoDBRepository;
import edu.northeastern.cs5500.starterbot.repository.MongoDBRepository_Factory;
import edu.northeastern.cs5500.starterbot.repository.RepositoryModule;
import edu.northeastern.cs5500.starterbot.repository.RepositoryModule_ProvideBattleFactory;
import edu.northeastern.cs5500.starterbot.repository.RepositoryModule_ProvideBattleRepositoryFactory;
import edu.northeastern.cs5500.starterbot.repository.RepositoryModule_ProvidePokedexEntryFactory;
import edu.northeastern.cs5500.starterbot.repository.RepositoryModule_ProvidePokedexEntryRepositoryFactory;
import edu.northeastern.cs5500.starterbot.repository.RepositoryModule_ProvidePokemonFactory;
import edu.northeastern.cs5500.starterbot.repository.RepositoryModule_ProvidePokemonRepositoryFactory;
import edu.northeastern.cs5500.starterbot.repository.RepositoryModule_ProvideTradeFactory;
import edu.northeastern.cs5500.starterbot.repository.RepositoryModule_ProvideTradeRepositoryFactory;
import edu.northeastern.cs5500.starterbot.repository.RepositoryModule_ProvideUserInventoryFactory;
import edu.northeastern.cs5500.starterbot.repository.RepositoryModule_ProvideUserInventoryRepositoryFactory;
import edu.northeastern.cs5500.starterbot.service.MongoDBService;
import edu.northeastern.cs5500.starterbot.service.MongoDBService_Factory;
import edu.northeastern.cs5500.starterbot.service.PokemonShowdownService;
import edu.northeastern.cs5500.starterbot.service.PokemonShowdownService_Factory;
import edu.northeastern.cs5500.starterbot.utils.BattleMessageUtil;
import edu.northeastern.cs5500.starterbot.utils.BattleMessageUtil_Factory;
import edu.northeastern.cs5500.starterbot.utils.TradeMessageUtil;
import edu.northeastern.cs5500.starterbot.utils.TradeMessageUtil_Factory;
import java.util.Set;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
final class DaggerBotComponent implements BotComponent {
  private final CommandModule commandModule;

  private final DaggerBotComponent botComponent = this;

  private Provider<Class<PokedexEntry>> providePokedexEntryProvider;

  private Provider<MongoDBService> mongoDBServiceProvider;

  private Provider<MongoDBRepository<PokedexEntry>> mongoDBRepositoryProvider;

  private Provider<GenericRepository<PokedexEntry>> providePokedexEntryRepositoryProvider;

  private Provider<PokemonShowdownService> pokemonShowdownServiceProvider;

  private Provider<PokedexController> pokedexControllerProvider;

  private Provider<CatchCommand> catchCommandProvider;

  private Provider<Class<Trade>> provideTradeProvider;

  private Provider<MongoDBRepository<Trade>> mongoDBRepositoryProvider2;

  private Provider<GenericRepository<Trade>> provideTradeRepositoryProvider;

  private Provider<Class<UserInventory>> provideUserInventoryProvider;

  private Provider<MongoDBRepository<UserInventory>> mongoDBRepositoryProvider3;

  private Provider<GenericRepository<UserInventory>> provideUserInventoryRepositoryProvider;

  private Provider<Class<Pokemon>> providePokemonProvider;

  private Provider<MongoDBRepository<Pokemon>> mongoDBRepositoryProvider4;

  private Provider<GenericRepository<Pokemon>> providePokemonRepositoryProvider;

  private Provider<PokemonController> pokemonControllerProvider;

  private Provider<UserInventoryController> userInventoryControllerProvider;

  private Provider<TradeController> tradeControllerProvider;

  private Provider<TradeCommand> tradeCommandProvider;

  private Provider<Class<Battle>> provideBattleProvider;

  private Provider<MongoDBRepository<Battle>> mongoDBRepositoryProvider5;

  private Provider<GenericRepository<Battle>> provideBattleRepositoryProvider;

  private Provider<BattleController> battleControllerProvider;

  private Provider<BattleCommand> battleCommandProvider;

  private Provider<ViewPartyCommand> viewPartyCommandProvider;

  private Provider<InspectPokemonCommand> inspectPokemonCommandProvider;

  private Provider<DeletePokemonCommand> deletePokemonCommandProvider;

  private Provider<ChooseStarterButtonClick> chooseStarterButtonClickProvider;

  private Provider<CatchButtonClick> catchButtonClickProvider;

  private Provider<TradeMessageUtil> tradeMessageUtilProvider;

  private Provider<TradeButtonClickHandleAccept> tradeButtonClickHandleAcceptProvider;

  private Provider<TradeButtonClickHandleDecline> tradeButtonClickHandleDeclineProvider;

  private Provider<TradeButtonClickHandleCancel> tradeButtonClickHandleCancelProvider;

  private Provider<TradeButtonClickHandleConfirm> tradeButtonClickHandleConfirmProvider;

  private Provider<BattleMessageUtil> battleMessageUtilProvider;

  private Provider<BattleButtonClickHandleAccept> battleButtonClickHandleAcceptProvider;

  private Provider<BattleButtonClickHandleDecline> battleButtonClickHandleDeclineProvider;

  private Provider<BattleButtonClickHandleCancel> battleButtonClickHandleCancelProvider;

  private Provider<BattleButtonClickHandleConfirm> battleButtonClickHandleConfirmProvider;

  private Provider<TradeSelectionMenuHandler> tradeSelectionMenuHandlerProvider;

  private Provider<BattleSelectionMenuHandler> battleSelectionMenuHandlerProvider;

  private Provider<GreetNewUserCommand> greetNewUserCommandProvider;

  private Provider<ChooseStarterCommand> chooseStarterCommandProvider;

  private Provider<GreetReturningUserCommand> greetReturningUserCommandProvider;

  private DaggerBotComponent(CommandModule commandModuleParam,
      RepositoryModule repositoryModuleParam) {
    this.commandModule = commandModuleParam;
    initialize(commandModuleParam, repositoryModuleParam);

  }

  public static Builder builder() {
    return new Builder();
  }

  public static BotComponent create() {
    return new Builder().build();
  }

  private SlashCommandHandler provideCatchCommand() {
    return CommandModule_ProvideCatchCommandFactory.provideCatchCommand(commandModule, catchCommandProvider.get());
  }

  private SlashCommandHandler provideTradeCommand() {
    return CommandModule_ProvideTradeCommandFactory.provideTradeCommand(commandModule, tradeCommandProvider.get());
  }

  private SlashCommandHandler provideBattleCommand() {
    return CommandModule_ProvideBattleCommandFactory.provideBattleCommand(commandModule, battleCommandProvider.get());
  }

  private SlashCommandHandler provideViewPartyCommand() {
    return CommandModule_ProvideViewPartyCommandFactory.provideViewPartyCommand(commandModule, viewPartyCommandProvider.get());
  }

  private SlashCommandHandler provideInspectPokemonCommand() {
    return CommandModule_ProvideInspectPokemonCommandFactory.provideInspectPokemonCommand(commandModule, inspectPokemonCommandProvider.get());
  }

  private SlashCommandHandler provideDeletePokemonCommand() {
    return CommandModule_ProvideDeletePokemonCommandFactory.provideDeletePokemonCommand(commandModule, deletePokemonCommandProvider.get());
  }

  private Set<SlashCommandHandler> setOfSlashCommandHandler() {
    return SetBuilder.<SlashCommandHandler>newSetBuilder(6).add(provideCatchCommand()).add(provideTradeCommand()).add(provideBattleCommand()).add(provideViewPartyCommand()).add(provideInspectPokemonCommand()).add(provideDeletePokemonCommand()).build();
  }

  private ButtonClickHandler provideChooseStarterButtonClickHandler() {
    return CommandModule_ProvideChooseStarterButtonClickHandlerFactory.provideChooseStarterButtonClickHandler(commandModule, chooseStarterButtonClickProvider.get());
  }

  private ButtonClickHandler provideCatchButtonClickHandler() {
    return CommandModule_ProvideCatchButtonClickHandlerFactory.provideCatchButtonClickHandler(commandModule, catchButtonClickProvider.get());
  }

  private ButtonClickHandler provideTradeButtonClickHandleAccept() {
    return CommandModule_ProvideTradeButtonClickHandleAcceptFactory.provideTradeButtonClickHandleAccept(commandModule, tradeButtonClickHandleAcceptProvider.get());
  }

  private ButtonClickHandler provideTradeButtonClickHandleDecline() {
    return CommandModule_ProvideTradeButtonClickHandleDeclineFactory.provideTradeButtonClickHandleDecline(commandModule, tradeButtonClickHandleDeclineProvider.get());
  }

  private ButtonClickHandler provideTradeButtonClickHandleCancel() {
    return CommandModule_ProvideTradeButtonClickHandleCancelFactory.provideTradeButtonClickHandleCancel(commandModule, tradeButtonClickHandleCancelProvider.get());
  }

  private ButtonClickHandler provideTradeConfirmClickHandler() {
    return CommandModule_ProvideTradeConfirmClickHandlerFactory.provideTradeConfirmClickHandler(commandModule, tradeButtonClickHandleConfirmProvider.get());
  }

  private ButtonClickHandler provideBattleButtonClickHandleAccept() {
    return CommandModule_ProvideBattleButtonClickHandleAcceptFactory.provideBattleButtonClickHandleAccept(commandModule, battleButtonClickHandleAcceptProvider.get());
  }

  private ButtonClickHandler provideBattleButtonClickHandleDecline() {
    return CommandModule_ProvideBattleButtonClickHandleDeclineFactory.provideBattleButtonClickHandleDecline(commandModule, battleButtonClickHandleDeclineProvider.get());
  }

  private ButtonClickHandler provideBattleButtonClickHandleCancel() {
    return CommandModule_ProvideBattleButtonClickHandleCancelFactory.provideBattleButtonClickHandleCancel(commandModule, battleButtonClickHandleCancelProvider.get());
  }

  private ButtonClickHandler provideBattleConfirmClickHandler() {
    return CommandModule_ProvideBattleConfirmClickHandlerFactory.provideBattleConfirmClickHandler(commandModule, battleButtonClickHandleConfirmProvider.get());
  }

  private Set<ButtonClickHandler> setOfButtonClickHandler() {
    return SetBuilder.<ButtonClickHandler>newSetBuilder(10).add(provideChooseStarterButtonClickHandler()).add(provideCatchButtonClickHandler()).add(provideTradeButtonClickHandleAccept()).add(provideTradeButtonClickHandleDecline()).add(provideTradeButtonClickHandleCancel()).add(provideTradeConfirmClickHandler()).add(provideBattleButtonClickHandleAccept()).add(provideBattleButtonClickHandleDecline()).add(provideBattleButtonClickHandleCancel()).add(provideBattleConfirmClickHandler()).build();
  }

  private SelectionMenuHandler provideTradeSelectionMenuHandler() {
    return CommandModule_ProvideTradeSelectionMenuHandlerFactory.provideTradeSelectionMenuHandler(commandModule, tradeSelectionMenuHandlerProvider.get());
  }

  private SelectionMenuHandler provideBattleSelectionMenuHandler() {
    return CommandModule_ProvideBattleSelectionMenuHandlerFactory.provideBattleSelectionMenuHandler(commandModule, battleSelectionMenuHandlerProvider.get());
  }

  private Set<SelectionMenuHandler> setOfSelectionMenuHandler() {
    return SetBuilder.<SelectionMenuHandler>newSetBuilder(2).add(provideTradeSelectionMenuHandler()).add(provideBattleSelectionMenuHandler()).build();
  }

  private MessageListener messageListener() {
    return injectMessageListener(MessageListener_Factory.newInstance());
  }

  private GuildListener guildListener() {
    return injectGuildListener(GuildListener_Factory.newInstance());
  }

  @SuppressWarnings("unchecked")
  private void initialize(final CommandModule commandModuleParam,
      final RepositoryModule repositoryModuleParam) {
    this.providePokedexEntryProvider = RepositoryModule_ProvidePokedexEntryFactory.create(repositoryModuleParam);
    this.mongoDBServiceProvider = DoubleCheck.provider(MongoDBService_Factory.create());
    this.mongoDBRepositoryProvider = MongoDBRepository_Factory.create(providePokedexEntryProvider, mongoDBServiceProvider);
    this.providePokedexEntryRepositoryProvider = RepositoryModule_ProvidePokedexEntryRepositoryFactory.create(repositoryModuleParam, mongoDBRepositoryProvider);
    this.pokemonShowdownServiceProvider = DoubleCheck.provider(PokemonShowdownService_Factory.create());
    this.pokedexControllerProvider = DoubleCheck.provider(PokedexController_Factory.create(providePokedexEntryRepositoryProvider, pokemonShowdownServiceProvider));
    this.catchCommandProvider = DoubleCheck.provider(CatchCommand_Factory.create(pokedexControllerProvider));
    this.provideTradeProvider = RepositoryModule_ProvideTradeFactory.create(repositoryModuleParam);
    this.mongoDBRepositoryProvider2 = MongoDBRepository_Factory.create(provideTradeProvider, mongoDBServiceProvider);
    this.provideTradeRepositoryProvider = RepositoryModule_ProvideTradeRepositoryFactory.create(repositoryModuleParam, mongoDBRepositoryProvider2);
    this.provideUserInventoryProvider = RepositoryModule_ProvideUserInventoryFactory.create(repositoryModuleParam);
    this.mongoDBRepositoryProvider3 = MongoDBRepository_Factory.create(provideUserInventoryProvider, mongoDBServiceProvider);
    this.provideUserInventoryRepositoryProvider = RepositoryModule_ProvideUserInventoryRepositoryFactory.create(repositoryModuleParam, mongoDBRepositoryProvider3);
    this.providePokemonProvider = RepositoryModule_ProvidePokemonFactory.create(repositoryModuleParam);
    this.mongoDBRepositoryProvider4 = MongoDBRepository_Factory.create(providePokemonProvider, mongoDBServiceProvider);
    this.providePokemonRepositoryProvider = RepositoryModule_ProvidePokemonRepositoryFactory.create(repositoryModuleParam, mongoDBRepositoryProvider4);
    this.pokemonControllerProvider = DoubleCheck.provider(PokemonController_Factory.create(providePokemonRepositoryProvider, pokedexControllerProvider));
    this.userInventoryControllerProvider = DoubleCheck.provider(UserInventoryController_Factory.create(provideUserInventoryRepositoryProvider, pokemonControllerProvider));
    this.tradeControllerProvider = DoubleCheck.provider(TradeController_Factory.create(provideTradeRepositoryProvider, userInventoryControllerProvider));
    this.tradeCommandProvider = DoubleCheck.provider(TradeCommand_Factory.create(tradeControllerProvider));
    this.provideBattleProvider = RepositoryModule_ProvideBattleFactory.create(repositoryModuleParam);
    this.mongoDBRepositoryProvider5 = MongoDBRepository_Factory.create(provideBattleProvider, mongoDBServiceProvider);
    this.provideBattleRepositoryProvider = RepositoryModule_ProvideBattleRepositoryFactory.create(repositoryModuleParam, mongoDBRepositoryProvider5);
    this.battleControllerProvider = DoubleCheck.provider(BattleController_Factory.create(provideBattleRepositoryProvider, pokemonControllerProvider, userInventoryControllerProvider));
    this.battleCommandProvider = DoubleCheck.provider(BattleCommand_Factory.create(battleControllerProvider));
    this.viewPartyCommandProvider = DoubleCheck.provider(ViewPartyCommand_Factory.create(userInventoryControllerProvider, pokemonControllerProvider));
    this.inspectPokemonCommandProvider = DoubleCheck.provider(InspectPokemonCommand_Factory.create(userInventoryControllerProvider, pokemonControllerProvider));
    this.deletePokemonCommandProvider = DoubleCheck.provider(DeletePokemonCommand_Factory.create(userInventoryControllerProvider, pokemonControllerProvider));
    this.chooseStarterButtonClickProvider = DoubleCheck.provider(ChooseStarterButtonClick_Factory.create(userInventoryControllerProvider, pokemonControllerProvider));
    this.catchButtonClickProvider = DoubleCheck.provider(CatchButtonClick_Factory.create(userInventoryControllerProvider, pokemonControllerProvider));
    this.tradeMessageUtilProvider = DoubleCheck.provider(TradeMessageUtil_Factory.create(tradeControllerProvider, userInventoryControllerProvider, pokemonControllerProvider));
    this.tradeButtonClickHandleAcceptProvider = DoubleCheck.provider(TradeButtonClickHandleAccept_Factory.create(tradeControllerProvider, userInventoryControllerProvider, pokemonControllerProvider, tradeMessageUtilProvider));
    this.tradeButtonClickHandleDeclineProvider = DoubleCheck.provider(TradeButtonClickHandleDecline_Factory.create(tradeControllerProvider, tradeMessageUtilProvider));
    this.tradeButtonClickHandleCancelProvider = DoubleCheck.provider(TradeButtonClickHandleCancel_Factory.create(tradeControllerProvider, tradeMessageUtilProvider));
    this.tradeButtonClickHandleConfirmProvider = DoubleCheck.provider(TradeButtonClickHandleConfirm_Factory.create(tradeControllerProvider, pokemonControllerProvider, tradeMessageUtilProvider));
    this.battleMessageUtilProvider = DoubleCheck.provider(BattleMessageUtil_Factory.create(battleControllerProvider, userInventoryControllerProvider, pokemonControllerProvider));
    this.battleButtonClickHandleAcceptProvider = DoubleCheck.provider(BattleButtonClickHandleAccept_Factory.create(battleControllerProvider, userInventoryControllerProvider, battleMessageUtilProvider));
    this.battleButtonClickHandleDeclineProvider = DoubleCheck.provider(BattleButtonClickHandleDecline_Factory.create(battleControllerProvider, battleMessageUtilProvider));
    this.battleButtonClickHandleCancelProvider = DoubleCheck.provider(BattleButtonClickHandleCancel_Factory.create(battleControllerProvider, battleMessageUtilProvider));
    this.battleButtonClickHandleConfirmProvider = DoubleCheck.provider(BattleButtonClickHandleConfirm_Factory.create(battleControllerProvider, battleMessageUtilProvider));
    this.tradeSelectionMenuHandlerProvider = DoubleCheck.provider(TradeSelectionMenuHandler_Factory.create(tradeControllerProvider, userInventoryControllerProvider, pokemonControllerProvider, tradeMessageUtilProvider));
    this.battleSelectionMenuHandlerProvider = DoubleCheck.provider(BattleSelectionMenuHandler_Factory.create(battleControllerProvider, pokemonControllerProvider, battleMessageUtilProvider));
    this.greetNewUserCommandProvider = DoubleCheck.provider(GreetNewUserCommand_Factory.create());
    this.chooseStarterCommandProvider = DoubleCheck.provider(ChooseStarterCommand_Factory.create());
    this.greetReturningUserCommandProvider = DoubleCheck.provider(GreetReturningUserCommand_Factory.create());
  }

  @Override
  public Bot bot() {
    return injectBot(Bot_Factory.newInstance());
  }

  private MessageListener injectMessageListener(MessageListener instance) {
    MessageListener_MembersInjector.injectCommands(instance, setOfSlashCommandHandler());
    MessageListener_MembersInjector.injectButtons(instance, setOfButtonClickHandler());
    MessageListener_MembersInjector.injectSelectionMenus(instance, setOfSelectionMenuHandler());
    return instance;
  }

  private GuildListener injectGuildListener(GuildListener instance) {
    GuildListener_MembersInjector.injectGreetNewUserCommand(instance, greetNewUserCommandProvider.get());
    GuildListener_MembersInjector.injectChooseStarterCommand(instance, chooseStarterCommandProvider.get());
    GuildListener_MembersInjector.injectUserInventoryController(instance, userInventoryControllerProvider.get());
    GuildListener_MembersInjector.injectGreetReturningUserCommand(instance, greetReturningUserCommandProvider.get());
    return instance;
  }

  private Bot injectBot(Bot instance) {
    Bot_MembersInjector.injectMessageListener(instance, messageListener());
    Bot_MembersInjector.injectGuildListener(instance, guildListener());
    return instance;
  }

  static final class Builder {
    private CommandModule commandModule;

    private RepositoryModule repositoryModule;

    private Builder() {
    }

    public Builder commandModule(CommandModule commandModule) {
      this.commandModule = Preconditions.checkNotNull(commandModule);
      return this;
    }

    public Builder repositoryModule(RepositoryModule repositoryModule) {
      this.repositoryModule = Preconditions.checkNotNull(repositoryModule);
      return this;
    }

    public BotComponent build() {
      if (commandModule == null) {
        this.commandModule = new CommandModule();
      }
      if (repositoryModule == null) {
        this.repositoryModule = new RepositoryModule();
      }
      return new DaggerBotComponent(commandModule, repositoryModule);
    }
  }
}
